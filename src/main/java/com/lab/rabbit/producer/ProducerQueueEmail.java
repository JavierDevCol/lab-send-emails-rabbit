package com.lab.rabbit.producer;

import com.lab.rabbit.model.MetaDataEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProducerQueueEmail {

    private final Logger log = LoggerFactory.getLogger(ProducerQueueEmail.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    public void producerQueueEmail(MetaDataEmail metaDataEmail) {
        log.info("EmailSender || Objecto entrante: {}", metaDataEmail);
        rabbitTemplate.convertAndSend(exchange, routingKey, metaDataEmail);
    }
}
