package com.lab.rabbit.producer;

import com.lab.rabbit.model.MetaDataEmail;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ProducerQueueEmailIntegrationTest {
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    @Autowired
    private ProducerQueueEmail producerQueueEmail;

    @Test
    public void producerQueueEmail_ShouldSendMessaggeToRabbit() {
        MetaDataEmail metaDataEmail = this.crearEmail("jbgm93@gmail.com");

        //when
        producerQueueEmail.producerQueueEmail(metaDataEmail);

        //Then
        Mockito.verify(
                rabbitTemplate, Mockito.times(1)
        ).convertAndSend(exchange, routingKey, metaDataEmail);

    }

    private MetaDataEmail crearEmail(String email) {
        String subject = "Test email";
        String body = "This is a test email";
        return new MetaDataEmail(email, subject, body);
    }
}
