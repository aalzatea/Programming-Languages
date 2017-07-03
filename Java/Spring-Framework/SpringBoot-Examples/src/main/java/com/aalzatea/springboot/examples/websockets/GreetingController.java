package com.aalzatea.springboot.examples.websockets;

import com.aalzatea.springboot.examples.model.websockets.Greeting;
import com.aalzatea.springboot.examples.model.websockets.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by aalzate on 7/3/17.
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000);
        return new Greeting("Hello " + message.getName() + "!");
    }
}
