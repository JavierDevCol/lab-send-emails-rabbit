package com.lab.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitApplication {

    private final static Logger log = LoggerFactory.getLogger(RabbitApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);
        log.info("RabbitApplication || APPLICATION STARTED");
    }

}
