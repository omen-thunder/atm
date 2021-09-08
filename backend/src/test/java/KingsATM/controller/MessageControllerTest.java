package KingsATM.controller;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageControllerTest {

    @Autowired
    private MessageController controller;

    @Test
    public void hello() {
        assertEquals(controller.hello(), "Hello from the message controller");
    }
}