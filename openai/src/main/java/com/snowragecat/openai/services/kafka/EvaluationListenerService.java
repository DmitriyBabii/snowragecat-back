package com.snowragecat.openai.services.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snowragecat.openai.services.OpenAiService;
import com.snowragecat.shared.configs.KafkaConfig;
import com.snowragecat.shared.dtos.EvaluateResponse;
import com.snowragecat.shared.dtos.EvaluationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EvaluationListenerService {
    private final EvaluationSenderService evaluationSenderService;
    private final OpenAiService openAiService;

    @KafkaListener(topics = KafkaConfig.LEAD_EVALUATE_TOPIC, groupId = KafkaConfig.LEAD_EVALUATE_OPENAI_GROUP)
    public void evaluateLead(EvaluationRequest evaluationRequest) {
        try {
            log.info("Get evaluation request for {}", evaluationRequest.email());
            EvaluateResponse evaluateResponse = openAiService.evaluateLead(evaluationRequest);
            evaluationSenderService.sendToVerdict(evaluateResponse);
        } catch (JsonProcessingException e) {
            log.error("Cant get OpenAi response: {}", e.getMessage());
        }
    }
}
