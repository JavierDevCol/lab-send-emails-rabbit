package com.lab.rabbit.service;


import com.lab.rabbit.Exceptions.ExceptionInvalidEmail;
import com.lab.rabbit.consumer.service.SendEmailService;
import com.lab.rabbit.model.MetaDataEmail;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Objects;

@SpringBootTest
class SendEmailServiceTest {

    @MockBean
    private JavaMailSender emailSender;

    @Autowired
    private SendEmailService sendEmailService;

    @Test
    void sendEmail_shouldSendEmailSuccessfully() throws MessagingException {
        MetaDataEmail metaDataEmail = crearEmail("jbgm93@gmail.com");

        // When
        sendEmailService.sendEmail(metaDataEmail);

        // Then
        ArgumentCaptor<SimpleMailMessage> messageCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        Mockito.verify(
                emailSender,
                Mockito.times(1)).send(messageCaptor.capture()
        );
        SimpleMailMessage message = messageCaptor.getValue();
        Assertions.assertEquals(metaDataEmail.getTo(), Objects.requireNonNull(message.getTo())[0]);
        Assertions.assertEquals(metaDataEmail.getSubject(), message.getSubject());
        Assertions.assertEquals(metaDataEmail.getBody(), message.getText());
    }

    @Test
    void sendEmail_shouldInvalidEmail() {
        MetaDataEmail metaDataEmail = crearEmail("jbgm93gmail.com");

        // When
        Assertions.assertThrows(
                ExceptionInvalidEmail.class,
                () -> sendEmailService.sendEmail(metaDataEmail)
        );

        // Then
        Mockito.verify(
                emailSender,
                Mockito.never()).send(Mockito.any(SimpleMailMessage.class)
        );
    }

    private MetaDataEmail crearEmail(String email) {
        // Given
        String subject = "Test email";
        String body = "This is a test email";
        return new MetaDataEmail(email, subject, body);
    }
}

