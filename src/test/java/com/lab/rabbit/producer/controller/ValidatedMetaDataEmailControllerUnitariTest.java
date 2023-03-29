package com.lab.rabbit.producer.controller;

import com.lab.rabbit.model.MetaDataEmail;
import com.lab.rabbit.producer.service.ValidatedMetaDataEmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ValidatedMetaDataEmailControllerUnitariTest {

    @MockBean
    private ValidatedMetaDataEmailService validatedMetaDataEmailService;

    @Autowired
    private ValidatedMetaDataEmailController validatedMetaDataEmailController;

    @Test
    public void sendMessaggeSuccessTest() throws Exception {
        Mockito.doNothing().when(validatedMetaDataEmailService).validEmailProducer(Mockito.any(MetaDataEmail.class));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(validatedMetaDataEmailController).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/productor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"email\": \"test@test.com\", \"subject\": \"Test email\", \"body\": \"This is a test email\" }")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Mockito.verify(validatedMetaDataEmailService, Mockito.times(1)).validEmailProducer(Mockito.any(MetaDataEmail.class));
    }
}
