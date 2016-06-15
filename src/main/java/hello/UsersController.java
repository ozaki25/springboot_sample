package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    @RequestMapping(method = RequestMethod.GET)
    public User index() {
        return new User("ozaki", 25);
    }
}
