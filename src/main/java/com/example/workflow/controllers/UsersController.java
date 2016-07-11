package com.example.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final Log logger = LogFactory.getLog(UsersController.class);
    @Autowired
    private UserRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> index(@RequestParam MultiValueMap<String, String> query) {
        logger.info("query: " + query);
        String team = query.toSingleValueMap().get("team");
        String jobLevelLteString = query.toSingleValueMap().get("jobLevel[lte]");
        Integer jobLevelLte = jobLevelLteString == null ? null : Integer.parseInt(jobLevelLteString);

        List<User> users = new ArrayList<User>();
        if(team != null && jobLevelLte != null) {
            logger.info("findByTeamAndJobLevelLessThanEqual");
            users = repository.findByTeamAndJobLevelLessThanEqual(team, jobLevelLte);
        } else if(team != null) {
            logger.info("findByTeam");
            users = repository.findByTeam(team);
        } else if(jobLevelLte != null) {
            logger.info("findByJobLevelLessThanEqual");
            users = repository.findByJobLevelLessThanEqual(jobLevelLte);
        } else {
            logger.info("findAll");
            users = repository.findAll();
        }
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
