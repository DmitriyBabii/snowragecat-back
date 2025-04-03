package com.snowragecat.main.jpa.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class LeadAmqpListenerService {

    @RabbitListener(queues = "")
    public void leadEvaluate(){

    }
}
