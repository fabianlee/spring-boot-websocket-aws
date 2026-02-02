package org.fabianlee.spring_boot_websocket_aws.messagingstompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message) throws Exception {

    // Immediate first response
    new Thread(() -> {
        try {
            Thread.sleep(2000); // Simulate delay
            // Send second response manually
            messagingTemplate.convertAndSend("/topic/greetings", new Greeting("Follow-up message"));
        } catch (InterruptedException e) { e.printStackTrace(); }
    }).start();    

    //Thread.sleep(1000); // simulated delay
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }


}
