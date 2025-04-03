package com.snowragecat.main.jpa.services;

import com.snowragecat.main.jpa.mappers.LeadMapper;
import com.snowragecat.main.jpa.models.dtos.LeadFormRequest;
import com.snowragecat.main.jpa.models.dtos.LeadFormResponse;
import com.snowragecat.main.jpa.models.entities.Lead;
import com.snowragecat.main.jpa.repositories.LeadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadService {
    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;
    private final LeadAmqpSenderService leadAmqpSenderService;

    public void processLeadData(LeadFormRequest leadFormRequest) {
        leadAmqpSenderService.sendLeadData(leadFormRequest);
    }

    public void save(LeadFormRequest leadFormRequest) {
        Lead lead = leadMapper.toEntity(leadFormRequest);
        leadRepository.save(lead);
    }

}
