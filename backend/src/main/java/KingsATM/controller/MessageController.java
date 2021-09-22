package KingsATM.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/messages")
public class MessageController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from the message controller";
    }

    @GetMapping("/hi")
    public String hi() {
        return "Hi from the message controller";
    }

}
