package com.snowragecat.openai.services;

import com.snowragecat.openai.configs.KafkaConfig;
import com.snowragecat.shared.dtos.EvaluateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadSenderService {
    private final KafkaTemplate<String, EvaluateResponse> kafkaTemplate;

    public void sendToVerdict(EvaluateResponse evaluateResponse) {
        kafkaTemplate.send(KafkaConfig.LEAD_VERDICT_TOPIC, evaluateResponse);
    }
}
