package com.snowragecat.main.jpa.services;

import com.snowragecat.main.jpa.models.entities.Evaluation;
import com.snowragecat.main.jpa.models.entities.Lead;
import com.snowragecat.main.jpa.models.enums.LeadStatus;
import com.snowragecat.main.jpa.repositories.EvaluationRepository;
import com.snowragecat.main.jpa.repositories.LeadRepository;
import com.snowragecat.shared.dtos.EvaluateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final LeadRepository leadRepository;

    public void save(EvaluateResponse evaluateResponse) {
        if (evaluateResponse.leadId() == null) {
            return;
        }
        Optional<Lead> lead = leadRepository.findById(evaluateResponse.leadId());

        lead.ifPresent(l -> {
            Evaluation evaluation = mapToEntity(evaluateResponse, l);
            evaluationRepository.save(evaluation);
            log.info("Evaluation was saved");
        });
    }

    private static Evaluation mapToEntity(EvaluateResponse evaluateResponse, Lead lead) {
        return Evaluation.builder()
                .score(evaluateResponse.score())
                .comment(evaluateResponse.comment())
                .message(evaluateResponse.message())
                .status(LeadStatus.PENDING)
                .lead(lead)
                .build();
    }
}
