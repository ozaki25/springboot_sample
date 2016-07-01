package com.example.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class App {
    @RequestMapping("/")
    public String home() {
        return "index";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
