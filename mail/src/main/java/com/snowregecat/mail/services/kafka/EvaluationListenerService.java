package com.snowregecat.mail.services.kafka;

import com.snowragecat.shared.configs.KafkaConfig;
import com.snowragecat.shared.dtos.EvaluateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EvaluationListenerService {
    private static final String SUBJECT = "Crm response";

    private final MailService mailService;

    @KafkaListener(topics = KafkaConfig.EVALUATE_VERDICT_TOPIC, groupId = KafkaConfig.EVALUATE_VERDICT_MAIL_GROUP)
    public void sendEvaluateMail(EvaluateResponse evaluateResponse) {
        mailService.sendMail(evaluateResponse.email(), SUBJECT, evaluateResponse.message());
    }
}
