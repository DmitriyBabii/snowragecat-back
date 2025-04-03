package com.snowragecat.main.rabbitmg;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowragecat.main.configs.RabbitMqConfig;
import com.snowragecat.main.jpa.models.dtos.LeadFormRequest;
import com.snowragecat.main.openai.services.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMqConsumer {
    private final OpenAiService openAiService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMqConfig.LEAD_QUEUE)
    public void receiveLead(LeadFormRequest leadFormRequest) {
        log.info("Receive lead for {}", leadFormRequest.email());
        try {
            openAiService.evaluateLead(objectMapper.writeValueAsString(leadFormRequest));
            log.info("Get response for {}", leadFormRequest.email());
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
