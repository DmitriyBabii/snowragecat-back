package com.snowragecat.openai.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConfig {
    public static final String LEAD_EVALUATE_TOPIC = "lead_evaluate";
    public static final String LEAD_EVALUATE_OPENAI = "lead_evaluate_openai";

    public static final String LEAD_VERDICT_TOPIC = "lead_verdict";
}
