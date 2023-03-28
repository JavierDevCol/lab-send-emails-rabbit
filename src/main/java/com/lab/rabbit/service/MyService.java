package com.lab.rabbit.service;

import com.lab.rabbit.model.Email;
import com.lab.rabbit.productor.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private  final Logger log = LoggerFactory.getLogger(MyService.class);
    @Autowired
    private EmailSender emailSender;

    public void sendEmail(Email emailMessage) {
        log.info("MyService || llegaron los mensajes: {}", emailMessage);
        Email email = new Email();
        email.setTo(emailMessage.getTo());
        email.setSubject(emailMessage.getSubject());
        email.setBody(emailMessage.getBody());
        emailSender.sendEmail(email);
    }
}