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
    @Autowired
    private StatusRepository statusRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<User> users = userRepository.findAll();
        if(users.size() == 0)  users = addDefaultUser();
        if(statusRepository.findAll().size() == 0) addDefaultStatus();
        model.addAttribute("users", users);
        return "login";
    }

    private List<User> addDefaultUser() {
        User[] users = {
            new User("001", "アドミンユーザ", "管理チーム", 0, true),
            new User("002", "一般ユーザ", "開発チーム", 3, false),
            new User("003", "役席ユーザ", "開発チーム", 2, false),
            new User("004", "受付ユーザ", "運用チーム", 2, true),
            new User("005", "担当ユーザ", "運用チーム", 4, false)
        };
        for(User user : users) userRepository.save(user);
        return userRepository.findAll();
    }

    private void addDefaultStatus() {
        String[] statusList = {
            "作成中",
            "承認待ち",
            "受付待ち",
            "作業完了待ち",
            "作業完了承認待ち",
            "作業確認待ち",
            "完了"
        };
        int i = 1;
        for(String name : statusList) {
            Status status = new Status(i, name);
            statusRepository.save(status);
            i++;
        }
    }
}
