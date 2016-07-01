package com.example.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private final Log logger = LogFactory.getLog(UsersController.class);
    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> index() {
        List<User> users = repository.findAll();
        logger.info(User.toString(users));
        return users;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User show(@PathVariable Long id) {
        User u = repository.findById(id);
        logger.info(u.toString());
        return u;
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        User u = repository.save(user);
        logger.info(u.toString());
        return u;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        User u = repository.save(user);
        logger.info(u.toString());
        return u;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable Long id){
        User u = repository.findById(id);
        logger.info(u.toString());
        repository.delete(id);
        return u;
    }
}
