package com.lab.rabbit.service;

import com.lab.rabbit.model.Email;
import com.lab.rabbit.productor.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private EmailSender emailSender;

    public void sendEmail(String to, String subject, String body) {
        Email email = new Email();
        email.setTo(to);
        email.setSubject(subject);
        email.setBody(body);
        emailSender.sendEmail(email);
    }
}