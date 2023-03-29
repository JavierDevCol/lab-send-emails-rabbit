package com.lab.rabbit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MetaDataEmail {
    private String to;
    private String subject;
    private String body;

}
