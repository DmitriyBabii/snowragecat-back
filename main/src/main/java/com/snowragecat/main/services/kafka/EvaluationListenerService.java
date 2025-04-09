package com.snowragecat.main.services.kafka;

import com.snowragecat.main.services.jpa.EvaluationService;
import com.snowragecat.shared.configs.KafkaConfig;
import com.snowragecat.shared.dtos.EvaluateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EvaluationListenerService {
    private final EvaluationService evaluationService;

    @KafkaListener(topics = KafkaConfig.EVALUATE_VERDICT_TOPIC, groupId = KafkaConfig.EVALUATE_VERDICT_JPA_GROUP)
    public void saveVerdictLead(EvaluateResponse evaluateResponse) {
        log.info("Received evaluation response for {}", evaluateResponse.email());
        evaluationService.save(evaluateResponse);
    }
}
