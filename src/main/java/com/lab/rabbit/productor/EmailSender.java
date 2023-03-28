package com.lab.rabbit.productor;

import com.lab.rabbit.model.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    private final Logger log = LoggerFactory.getLogger(EmailSender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    public void sendEmail(Email email) {
        log.info("EmailSender || Objecto entrante: {}", email);
        rabbitTemplate.convertAndSend(exchange, routingKey, email);
    }
}
