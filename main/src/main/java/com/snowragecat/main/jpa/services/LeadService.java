package com.snowragecat.main.jpa.services;

import com.snowragecat.main.jpa.mappers.LeadMapper;
import com.snowragecat.main.jpa.models.dtos.LeadFormRequest;
import com.snowragecat.main.jpa.models.entities.Lead;
import com.snowragecat.main.jpa.repositories.LeadRepository;
import com.snowragecat.shared.dtos.EvaluationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadService {
    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;
    private final LeadSenderService leadSenderService;

    public void saveAndProcessLeadData(LeadFormRequest leadFormRequest) {
        Lead lead = leadMapper.toEntity(leadFormRequest);
        Lead saved = leadRepository.saveAndFlush(lead);
        EvaluationRequest evaluation = leadMapper.toEvaluation(saved);
        leadSenderService.sendToEvaluate(evaluation);
    }

    public List<Lead> findAll(){
        return leadRepository.findAll();
    }
}
