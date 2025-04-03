package com.snowragecat.main.jpa.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowragecat.main.configs.RabbitMqConfig;
import com.snowragecat.main.jpa.models.dtos.LeadFormRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadAmqpSenderService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public void sendLeadData(LeadFormRequest leadFormRequest) {
        try {
            String request = objectMapper.writeValueAsString(leadFormRequest);
            rabbitTemplate.convertAndSend(RabbitMqConfig.LEAD_EXCHANGE, RabbitMqConfig.LEAD_SEND_KEY, request);
        } catch (JsonProcessingException e) {
            log.error("Can't send lead data: {}", e.getMessage());
        }
    }


}
