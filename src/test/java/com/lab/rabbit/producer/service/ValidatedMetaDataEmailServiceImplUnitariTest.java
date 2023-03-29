package com.lab.rabbit.producer.service;

import com.lab.rabbit.Exceptions.ExceptionInvalidEmail;
import com.lab.rabbit.model.MetaDataEmail;
import com.lab.rabbit.producer.ProducerQueueEmail;
import com.lab.rabbit.producer.service.impl.ValidatedMetaDataEmailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidatedMetaDataEmailServiceImplUnitariTest {

    @Mock
    private ProducerQueueEmail producerQueueEmail;

    @InjectMocks
    private ValidatedMetaDataEmailServiceImpl validatedMetaDataEmailService;

    @Test
    public void testInvalidEmailProducer() {
        Assertions.assertThrows(
                ExceptionInvalidEmail.class,
                () -> validatedMetaDataEmailService.validEmailProducer(crearEmail("examplegmail.com"))
        );
        Mockito.verify(producerQueueEmail, Mockito.never()).producerQueueEmail(Mockito.any(MetaDataEmail.class));
    }

    @Test
    public void testValidEmailProducer() {
        Assertions.assertAll(
                () -> validatedMetaDataEmailService.validEmailProducer(crearEmail("example@gmail.com"))
        );
        Mockito.verify(producerQueueEmail, Mockito.times(1)).producerQueueEmail(Mockito.any(MetaDataEmail.class));
    }

    private MetaDataEmail crearEmail(String email) {
        String subject = "Test email";
        String body = "This is a test email";
        return new MetaDataEmail(email, subject, body);
    }
}
