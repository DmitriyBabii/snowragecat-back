package com.snowragecat.main.jpa.services;

import com.snowragecat.main.jpa.entities.Lead;
import com.snowragecat.main.jpa.repositories.LeadRepository;
import com.snowragecat.main.mappers.LeadMapper;
import com.snowragecat.main.models.dtos.LeadFormRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadService {
    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;

    public void saveAndProcessLead(LeadFormRequest leadFormRequest) {
        Lead lead = leadMapper.toEntity(leadFormRequest);
        Lead saved = leadRepository.save(lead);
        log.info("Lead with {} saved", saved.getEmail());
    }
}
