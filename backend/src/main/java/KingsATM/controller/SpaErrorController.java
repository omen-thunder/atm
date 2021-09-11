package KingsATM.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SpaErrorController implements ErrorController {

    @RequestMapping("/error")
    public Object error(HttpServletRequest request, HttpServletResponse response) {

        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            response.setStatus(HttpServletResponse.SC_OK);
            return "forward:/notFound"; // forward to static SPA html resource.
        } else {
            return "forward:/notFound";
//            return response; // or your REST 404 blabla...
        }
    }
}
