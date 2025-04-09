package com.snowregecat.mail.services.kafka;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;

    public void sendMail(String to, String subject, String text) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            mailSender.send(mimeMessage);

            log.info("Message was send to {}", to);
        } catch (MailException e) {
            log.error("Cant send email to {}: {}", to, e.getMessage());
        } catch (MessagingException e) {
            log.error("Cant create message to send: {}", e.getMessage());
        }
    }
}
