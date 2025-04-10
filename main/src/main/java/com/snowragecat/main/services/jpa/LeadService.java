package com.snowragecat.main.services.jpa;

import com.snowragecat.main.mappers.EvaluationMapper;
import com.snowragecat.main.mappers.LeadMapper;
import com.snowragecat.main.models.dtos.LeadBody;
import com.snowragecat.main.models.dtos.LeadFormRequest;
import com.snowragecat.main.models.entities.Evaluation;
import com.snowragecat.main.models.entities.Lead;
import com.snowragecat.main.repositories.EvaluationRepository;
import com.snowragecat.main.repositories.LeadRepository;
import com.snowragecat.main.services.kafka.EvaluationSenderService;
import com.snowragecat.shared.dtos.EvaluationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadService {
    private final LeadRepository leadRepository;
    private final LeadMapper leadMapper;
    private final EvaluationMapper evaluationMapper;
    private final EvaluationRepository evaluationRepository;
    private final EvaluationSenderService evaluationSenderService;

    public void saveAndProcessLeadData(LeadFormRequest leadFormRequest) {
        Lead lead = leadMapper.toEntity(leadFormRequest);
        Lead saved = leadRepository.saveAndFlush(lead);
        EvaluationRequest evaluation = leadMapper.toEvaluation(saved);
        evaluationSenderService.sendToEvaluate(evaluation);
    }

    public void save(LeadBody leadBody) {
        Lead lead = leadMapper.toEntity(leadBody);
        Evaluation evaluation = evaluationMapper.toEntity(leadBody.getEvaluation());
        evaluation.setLead(lead);
        leadRepository.saveAndFlush(lead);
        evaluationRepository.save(evaluation);
    }

    public Page<LeadBody> findAll(Pageable pageable) {
        return leadRepository.findAll(pageable).map(toDto());
    }

    public Optional<Lead> findById(Long id) {
        return leadRepository.findById(id);
    }

    private Function<Lead, LeadBody> toDto() {
        return lead -> {
            LeadBody dto = leadMapper.toDto(lead);
            dto.setEvaluation(evaluationMapper.toDto(lead.getEvaluation()));
            return dto;
        };
    }

}
