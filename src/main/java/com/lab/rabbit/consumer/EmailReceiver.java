package com.lab.rabbit.consumer;

import com.lab.rabbit.model.Email;
import com.lab.rabbit.service.EmailService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailReceiver {

    private final Logger log = LoggerFactory.getLogger(EmailReceiver.class);
    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveEmail(Email email) throws MessagingException {
        log.info("EmailReciver || email de la cola: {} ", email);
        emailService.sendEmail(email);
    }
}
