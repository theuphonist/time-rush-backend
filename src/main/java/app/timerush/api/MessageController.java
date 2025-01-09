package app.timerush.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/game/notifications")
    public Message greeting(Message message) throws Exception {
        System.out.println(message);
        Thread.sleep(1000); // simulated delay
        final Message response = new Message();
        response.setFrom("WebSocket Server");
        response.setContent("Hello from the server!");
        return response;
    }
}
