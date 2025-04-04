package com.snowragecat.main.jpa.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowragecat.main.configs.KafkaConfig;
import com.snowragecat.shared.dtos.EvaluateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadListenerService {
    private final EvaluationService evaluationService;

    @KafkaListener(topics = KafkaConfig.LEAD_VERDICT_TOPIC, groupId = "group")
    public void saveVerdictLead(EvaluateResponse evaluateResponse) {
        evaluationService.save(evaluateResponse);
    }
}
