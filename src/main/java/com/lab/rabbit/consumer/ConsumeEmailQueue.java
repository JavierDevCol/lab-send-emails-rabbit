package com.lab.rabbit.consumer;

import com.lab.rabbit.model.MetaDataEmail;
import com.lab.rabbit.consumer.service.SendEmailService;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumeEmailQueue {

    private final Logger log = LoggerFactory.getLogger(ConsumeEmailQueue.class);
    @Autowired
    private SendEmailService sendEmailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void consumeQueue(MetaDataEmail metaDataEmail) throws MessagingException {
        log.info("consumeQueue || email de la cola: {} ", metaDataEmail);
        sendEmailService.sendEmail(metaDataEmail);
    }
}
