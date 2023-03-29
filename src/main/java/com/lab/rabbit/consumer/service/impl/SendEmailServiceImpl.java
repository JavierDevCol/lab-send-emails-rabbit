package com.lab.rabbit.consumer.service.impl;

import com.lab.rabbit.consumer.service.SendEmailService;
import com.lab.rabbit.model.MetaDataEmail;
import com.lab.rabbit.util.Validators;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    private final JavaMailSender mailSender;

    public SendEmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(MetaDataEmail metaDataEmail) {
        Validators.emailValid(metaDataEmail.getTo());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(metaDataEmail.getTo());
        message.setSubject(metaDataEmail.getSubject());
        message.setText(metaDataEmail.getBody());
        mailSender.send(message);
    }
}

