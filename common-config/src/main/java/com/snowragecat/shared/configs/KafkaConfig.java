package com.snowragecat.shared.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaConfig {
    public static final String LEAD_EVALUATE_TOPIC = "lead_evaluate";
    public static final String LEAD_VERDICT_TOPIC = "lead_verdict";

    public static final String LEAD_EVALUATE_OPENAI = "lead_evaluate_openai";

    @Bean
    public NewTopic evaluateTopic() {
        return TopicBuilder.name(LEAD_EVALUATE_TOPIC).build();
    }

    @Bean
    public NewTopic verdictTopic() {
        return TopicBuilder.name(LEAD_VERDICT_TOPIC).build();
    }
}
