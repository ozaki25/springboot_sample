package com.example.workflow;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class App {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model model, Principal principal) {
        User user = new User();
        if(principal != null) user = userRepository.findByUid(principal.getName());
        model.addAttribute("user", user);
        return "index";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
