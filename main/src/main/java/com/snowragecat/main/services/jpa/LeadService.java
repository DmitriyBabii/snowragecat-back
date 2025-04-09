package com.snowragecat.main.services.jpa;

import com.snowragecat.main.mappers.LeadMapper;
import com.snowragecat.main.models.dtos.LeadFormRequest;
import com.snowragecat.main.models.entities.Lead;
import com.snowragecat.main.repositories.LeadRepository;
import com.snowragecat.main.services.kafka.EvaluationSenderService;
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
    private final EvaluationSenderService evaluationSenderService;

    public void saveAndProcessLeadData(LeadFormRequest leadFormRequest) {
        Lead lead = leadMapper.toEntity(leadFormRequest);
        Lead saved = leadRepository.saveAndFlush(lead);
        EvaluationRequest evaluation = leadMapper.toEvaluation(saved);
        evaluationSenderService.sendToEvaluate(evaluation);
    }

    public List<Lead> findAll(){
        return leadRepository.findAll();
    }
}
