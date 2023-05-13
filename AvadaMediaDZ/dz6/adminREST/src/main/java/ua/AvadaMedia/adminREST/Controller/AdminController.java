package ua.AvadaMedia.adminREST.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class AdminController {

    @GetMapping("/admin")
    private String startAdmin(
//            HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
//        response.getOutputStream().write("admin".getBytes());
        return "index";
    }

}