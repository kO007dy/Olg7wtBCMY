// 代码生成时间: 2025-09-19 10:34:02
package com.example.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableBinding(Sink.class)
public class MessageNotificationService {

    // The entry point of the Spring Boot application.
    public static void main(String[] args) {
        SpringApplication.run(MessageNotificationService.class, args);
    }

    // This is where we would define our service beans, message handlers, etc.
    // For brevity, those are omitted in this example.
}

/*
 * MessageService.java
 * 
 * A service interface for message handling.
 */
package com.example.notification.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageHeaderAccessor;

@EnableBinding(Processor.class)
@RestController
public class MessageService {

    private final Processor processor;

    public MessageService(Processor processor) {
        this.processor = processor;
    }

    @PostMapping("/sendNotification")
    public String sendMessage(@RequestBody String messageContent) {
        try {
            // Create a message with the content.
            Message<String> message = MessageBuilder.withPayload(messageContent).build();
            // Send the message to the processor output channel.
            this.processor.input().send(message);
            return "Message sent successfully";
        } catch (Exception e) {
            // Handle exceptions, log them, and return an error response.
            return "Error sending message: " + e.getMessage();
        }
    }
}

/*
 * MessageHandler.java
 * 
 * A message handler that processes incoming messages.
 */
package com.example.notification.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.cloud.stream.annotation.StreamListener;

public class MessageHandler {

    @StreamListener("input")
    public void receiveMessage(Message<String> message) {
        try {
            // Process the message payload.
            String payload = message.getPayload();
            // Log the message or perform other processing tasks.
            System.out.println("Received message: " + payload);
        } catch (MessagingException e) {
            // Handle any exceptions during message processing.
            e.printStackTrace();
        }
    }
}
