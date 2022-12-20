package com.example.backendproject;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/send/message")
    @SendTo("/topic/public")
    public GreetingREquest sendMessage(GreetingREquest message) {
        return message;
    }
}
