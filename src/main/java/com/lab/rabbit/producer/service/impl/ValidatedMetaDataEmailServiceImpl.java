package com.lab.rabbit.producer.service.impl;

import com.lab.rabbit.model.MetaDataEmail;
import com.lab.rabbit.producer.ProducerQueueEmail;
import com.lab.rabbit.producer.service.ValidatedMetaDataEmailService;
import com.lab.rabbit.util.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatedMetaDataEmailServiceImpl implements ValidatedMetaDataEmailService {

    private  final Logger log = LoggerFactory.getLogger(ValidatedMetaDataEmailServiceImpl.class);
    @Autowired
    private ProducerQueueEmail producerQueueEmail;

    public void validEmailProducer(MetaDataEmail metaDataEmail) {
        log.info("MyService || llegaron los mensajes: {}", metaDataEmail);
        Validators.emailValid(metaDataEmail.getTo());
        producerQueueEmail.producerQueueEmail(metaDataEmail);
    }
}