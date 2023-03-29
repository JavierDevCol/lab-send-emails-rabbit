package com.lab.rabbit.producer.controller;

import com.lab.rabbit.model.MetaDataEmail;
import com.lab.rabbit.producer.service.ValidatedMetaDataEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("productor")
public class ValidatedMetaDataEmailController {

    private final Logger log = LoggerFactory.getLogger(ValidatedMetaDataEmailController.class);
    private final ValidatedMetaDataEmailService validatedMetaDataEmailServiceImpl;

    public ValidatedMetaDataEmailController(ValidatedMetaDataEmailService validatedMetaDataEmailServiceImpl) {
        this.validatedMetaDataEmailServiceImpl = validatedMetaDataEmailServiceImpl;
    }

    @PostMapping
    public void sendMessagge(@RequestBody MetaDataEmail metaDataEmail) {
        log.info("Objecto recibi)o: {}", metaDataEmail.toString());
        this.validatedMetaDataEmailServiceImpl.validEmailProducer(metaDataEmail);
    }
}
