package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> index() {
        User user = new User("ozaki", 25);
        repository.save(user);
        return repository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User show(@PathVariable Long id) {
        return repository.findById(id);
    }
}
