package com.snowragecat.main.services.kafka;

import com.snowragecat.shared.configs.KafkaConfig;
import com.snowragecat.shared.dtos.EvaluationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EvaluationSenderService {
    private final KafkaTemplate<String, EvaluationRequest> kafkaTemplate;

    public void sendToEvaluate(EvaluationRequest evaluationRequest) {
        kafkaTemplate.send(KafkaConfig.LEAD_EVALUATE_TOPIC, evaluationRequest);
    }
}
