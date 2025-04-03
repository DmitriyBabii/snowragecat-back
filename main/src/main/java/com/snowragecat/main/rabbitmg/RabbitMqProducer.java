package com.snowragecat.main.rabbitmg;

import com.snowragecat.main.configs.RabbitMqConfig;
import com.snowragecat.main.jpa.models.dtos.LeadFormRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendLeadData(LeadFormRequest leadFormRequest) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.LEAD_EXCHANGE, RabbitMqConfig.LEAD_SEND_KEY, leadFormRequest);
    }
}
