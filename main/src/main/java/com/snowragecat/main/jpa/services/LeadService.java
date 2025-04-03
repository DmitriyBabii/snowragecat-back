package com.snowragecat.main.jpa.services;

import com.snowragecat.main.jpa.mappers.LeadMapper;
import com.snowragecat.main.jpa.models.dtos.LeadFormRequest;
import com.snowragecat.main.jpa.models.entities.Lead;
import com.snowragecat.main.jpa.repositories.LeadRepository;
import com.snowragecat.main.rabbitmg.RabbitMqProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadService {
    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;
    private final RabbitMqProducer rabbitMqProducer;

    public void saveAndProcessLead(LeadFormRequest leadFormRequest) {
        Lead lead = leadMapper.toEntity(leadFormRequest);

        leadRepository.save(lead);
        log.info("Lead with {} saved", lead.getEmail());

        rabbitMqProducer.sendLeadData(leadFormRequest);
    }
}
