package com.example.casestudyteam2.socket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {
    @MessageMapping("/hello")
    @SendTo("/topic/hi")
    public Hello greeting(User user) throws Exception {
        return new Hello(user.getName() + ":"+user.getMessage());
    }
}
