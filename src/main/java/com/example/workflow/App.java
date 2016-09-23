package com.example.workflow;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class App {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/current-user")
    public @ResponseBody User home(HttpServletRequest request, Principal principal) {
        User user = userRepository.findByUid(principal.getName());
        request.getSession().setAttribute("uid", user.getUid());
        request.getSession().setAttribute("name", user.getName());
        request.getSession().setAttribute("team", user.getTeam());
        return user;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
