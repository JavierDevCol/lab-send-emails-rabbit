package com.lab.rabbit.configure;

import com.lab.rabbit.converter.EmailMessageConverter;
import com.rabbitmq.client.ConnectionFactory;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    private final Logger log = LoggerFactory.getLogger(Beans.class);

    @Value("${jasypt.encryptor.password}")
    private String passEncryptor;
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost:15672");
        connectionFactory.setVirtualHost("guest");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        log.info("Beans || Conectado a rabbotMq");
        return connectionFactory.getRabbitConnectionFactory();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new EmailMessageConverter();
    }

    @Bean()
    @Qualifier("miEncryptor")
    public StringEncryptor stringEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(passEncryptor);
        return encryptor;
    }
}
