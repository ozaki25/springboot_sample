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
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DivisionRepository divisionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<User> users = userRepository.findAll();
        if(users.size() == 0)  users = addDefaultUser();
        if(statusRepository.findAll().size() == 0) addDefaultStatus();
        if(categoryRepository.findAll().size() == 0) addDefaultCategory();
        model.addAttribute("users", users);
        return "login";
    }

    private List<User> addDefaultUser() {
        User[] users = {
            new User("001", "アドミンユーザ", "管理チーム", 0, true),
            new User("002", "一般ユーザ", "開発チーム", 3, false),
            new User("003", "役席ユーザ", "開発チーム", 2, false),
            new User("004", "受付ユーザ", "運用チーム", 2, false),
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
            "完了"
        };
        int i = 1;
        for(String name : statusList) {
            Status status = new Status(i, name);
            statusRepository.save(status);
            i++;
        }
    }

    private void addDefaultCategory() {
        Category[] categories = {
            new Category("カテゴリ001", "001"),
            new Category("カテゴリ002", "002"),
            new Category("カテゴリ003", "003"),
            new Category("カテゴリ004", "004"),
            new Category("カテゴリ005", "005"),
        };
        for(Category category : categories) {
            Category c = categoryRepository.save(category);
            Division[] divisions = {
                new Division("種別" + c.getCode() + "001", c.getCode() + "001", c),
                new Division("種別" + c.getCode() + "002", c.getCode() + "002", c),
                new Division("種別" + c.getCode() + "003", c.getCode() + "003", c),
                new Division("種別" + c.getCode() + "004", c.getCode() + "004", c),
                new Division("種別" + c.getCode() + "005", c.getCode() + "005", c),
            };
            for(Division division : divisions) divisionRepository.save(division);
        }
    }
}
