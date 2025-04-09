package com.snowregecat.mail;

import com.snowragecat.shared.configs.KafkaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(KafkaConfig.class)
@SpringBootApplication
public class CrmMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmMailApplication.class, args);
    }

}
