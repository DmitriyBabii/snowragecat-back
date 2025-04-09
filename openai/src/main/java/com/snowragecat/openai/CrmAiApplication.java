package com.snowragecat.openai;

import com.snowragecat.shared.configs.KafkaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(KafkaConfig.class)
@SpringBootApplication
public class CrmAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmAiApplication.class, args);
    }

}
