package com.snowragecat.main.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String LEAD_QUEUE = "lead_queue_";
    public static final String LEAD_EXCHANGE = "lead_exchange";
    public static final String LEAD_SEND_KEY = "lead_send";

    @Bean
    public Queue leadQueue() {
        return new Queue(LEAD_QUEUE, true);
    }

    @Bean
    public DirectExchange leadExchange() {
        return new DirectExchange(LEAD_EXCHANGE);
    }

    @Bean
    public Binding leadSendBinding() {
        return BindingBuilder.bind(leadQueue())
                .to(leadExchange())
                .with(LEAD_SEND_KEY);
    }


    @Bean
    public ApplicationRunner adminInitializer(RabbitAdmin rabbitAdmin) {
        return args -> rabbitAdmin.initialize();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate) {
        return new RabbitAdmin(rabbitTemplate);
    }

//    @Bean
//    public MessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
}
