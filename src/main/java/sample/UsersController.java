package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> index() {
        User user = new User("ozaki", 25);
        repository.save(user);
        List<User> users = repository.findAll();
        System.out.println(User.toString(users));
        return users;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        User u = repository.save(user);
        System.out.println(u.toString());
        return u;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User show(@PathVariable Long id) {
        User user = repository.findById(id);
        System.out.println(user.toString());
        return user;
    }
}
