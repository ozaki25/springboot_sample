package com.example.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<User> users = userRepository.findAll();
        if(users.size() == 0) {
            User u1= new User("001", "アドミンユーザ", "管理チーム", 0, true);
            User u2= new User("002", "一般ユーザ", "開発チーム", 3, false);
            User u3= new User("003", "役席ユーザ", "開発チーム", 2, false);
            User u4= new User("004", "受付ユーザ", "運用チーム", 2, true);
            User u5= new User("005", "担当ユーザ", "運用チーム", 4, false);
            userRepository.save(u1);
            userRepository.save(u2);
            userRepository.save(u3);
            userRepository.save(u4);
            userRepository.save(u5);
            users = userRepository.findAll();
        }
        model.addAttribute("users", users);
        return "login";
    }

}
