package com.lab.rabbit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email {
    private String to;
    private String subject;
    private String body;

}
