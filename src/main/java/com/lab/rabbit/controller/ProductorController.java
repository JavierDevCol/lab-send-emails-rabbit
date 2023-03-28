package com.lab.rabbit.controller;

import com.lab.rabbit.model.Email;
import com.lab.rabbit.service.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("productor")
public class ProductorController {

    private final Logger log = LoggerFactory.getLogger(ProductorController.class);
    private final MyService myService;
    public ProductorController(MyService myService) {
        this.myService = myService;
    }

    @PostMapping
    public void sendMessagge(@RequestBody Email email) {
        log.info("Objecto recibi)o: {}", email.toString());
        this.myService.sendEmail(email);
    }
}
