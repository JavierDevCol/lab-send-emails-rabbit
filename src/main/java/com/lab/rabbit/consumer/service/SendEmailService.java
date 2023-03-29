package com.lab.rabbit.consumer.service;

import com.lab.rabbit.model.MetaDataEmail;
import jakarta.mail.MessagingException;

public interface SendEmailService {

    void sendEmail(MetaDataEmail metaDataEmail) throws MessagingException;
}
