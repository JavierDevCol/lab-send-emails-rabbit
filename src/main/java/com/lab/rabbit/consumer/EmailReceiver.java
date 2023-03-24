package com.lab.rabbit.consumer;

import com.lab.rabbit.model.Email;
import com.lab.rabbit.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class EmailReceiver {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receiveEmail(Email email) throws MessagingException {
        emailService.sendEmail(email);
    }
}
