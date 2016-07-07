package com.example.workflow;

import java.security.Principal;
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
    public @ResponseBody User home(Principal principal) {
        return userRepository.findByUid(principal.getName());
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
