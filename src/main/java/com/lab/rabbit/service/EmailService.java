package com.lab.rabbit.service;

import com.lab.rabbit.model.Email;
import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmail(Email email) throws MessagingException;
}
