package ua.AvadaMedia.adminREST.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.AvadaMedia.adminREST.RequestBody.SessionRequestBody;

import javax.servlet.http.HttpServletRequest;

@RestController
@Deprecated
public class Session {

    @PostMapping("/session")
    @CrossOrigin
    public boolean checkSession(HttpServletRequest httpServletRequest, @RequestBody SessionRequestBody requestBody) {
//        if (httpServletRequest.getSession(true).getAttribute(requestBody.getLogin()) != null)
//            if (httpServletRequest.getSession(true).getAttribute(requestBody.getLogin()).equals(requestBody.getLogin()))
//                return true;
//        if (requestBody.getLogin().equals("admin") && requestBody.getPassword().equals("admin")) {
//            httpServletRequest.getSession(true).setAttribute(requestBody.getLogin());
//            return true;
//        }
        return true;
    }

}
