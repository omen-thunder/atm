package KingsATM.controller;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageControllerTest {

    @Autowired
    private MessageController controller;

    @Test
    public void hello() {
        assertEquals(controller.hello(), "Hello from the message controller");
    }
}