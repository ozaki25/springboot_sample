package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/users-dev")
public class UsersConfirmController {
    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", repository.findAll());
        return "users/index";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newUser() {
        return "users/new";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", repository.findById(id));
        return "users/edit";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("user", repository.findById(id));
        return "users/show";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute User user) {
        repository.save(user);
        return "redirect:/users-dev";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        repository.save(user);
        return "redirect:/users-dev";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id){
        repository.delete(id);
        return "redirect:/users-dev";
    }
}
