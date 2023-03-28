package com.lab.rabbit.converter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.rabbit.model.Email;

@Component
public class EmailMessageConverter implements MessageConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        try {
            Email email = (Email) object;
            byte[] bytes = objectMapper.writeValueAsBytes(email);
            messageProperties.setContentType(MediaType.APPLICATION_JSON_VALUE);
            messageProperties.setContentEncoding("UTF-8");
            return new Message(bytes, messageProperties);
        } catch (Exception e) {
            throw new MessageConversionException("Failed to convert object to message", e);
        }
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        try {
            byte[] bytes = message.getBody();
            return objectMapper.readValue(bytes, Email.class);
        } catch (Exception e) {
            throw new MessageConversionException("Failed to convert message to object", e);
        }
    }
}

