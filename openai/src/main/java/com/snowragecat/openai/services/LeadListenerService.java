package com.snowragecat.openai.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snowragecat.openai.configs.KafkaConfig;
import com.snowragecat.shared.dtos.EvaluationRequest;
import com.snowragecat.shared.dtos.EvaluateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadListenerService {
    private final LeadSenderService leadSenderService;
    private final OpenAiService openAiService;

    @KafkaListener(topics = KafkaConfig.LEAD_EVALUATE_TOPIC, groupId = KafkaConfig.LEAD_EVALUATE_OPENAI)
    public void evaluateLead(EvaluationRequest evaluationRequest) {
        try {
            EvaluateResponse evaluateResponse = openAiService.evaluateLead(evaluationRequest);
            leadSenderService.sendToVerdict(evaluateResponse);
        } catch (JsonProcessingException e) {
            log.error("Cant get OpenAi response: {}", e.getMessage());
        }
    }
}
