package ua.AvadaMedia.adminREST.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Controller
public class SecurityLoginController {

    @GetMapping("/login")
    public void loginPage(HttpServletResponse response) throws IOException {
        response.getOutputStream().write("login page".getBytes());
    }

}