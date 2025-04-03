package com.snowragecat.openai.services;

import com.snowragecat.openai.configs.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMqConsumer {
    private final OpenAiService openAiService;

    @RabbitListener(queues = RabbitMqConfig.LEAD_QUEUE)
    public String receiveLead(String leadRequest) {
        long timer1 = System.currentTimeMillis();
        String s = openAiService.evaluateLead(leadRequest);
        log.info("Time spend: {}", System.currentTimeMillis() - timer1);
        return s;
    }
}
