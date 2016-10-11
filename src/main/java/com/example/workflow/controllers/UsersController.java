package com.example.workflow;

import javax.servlet.http.HttpServletRequest;
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
    public List<User> index(HttpServletRequest httpRequest, @RequestParam MultiValueMap<String, String> query) {
        App.logging(logger, httpRequest);
        String team = query.toSingleValueMap().get("team");
        String jobLevelLteString = query.toSingleValueMap().get("jobLevel[lte]");
        Integer jobLevelLte = jobLevelLteString == null ? null : Integer.parseInt(jobLevelLteString);

        return (team != null && jobLevelLte != null) ? repository.findByTeamAndJobLevelLessThanEqual(team, jobLevelLte) :
               (team != null)                        ? repository.findByTeam(team) :
               (jobLevelLte != null)                 ? repository.findByJobLevelLessThanEqual(jobLevelLte) :
                                                       repository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User show(HttpServletRequest httpRequest, @PathVariable Long id) {
        App.logging(logger, httpRequest);
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(HttpServletRequest httpRequest, @RequestBody User user) {
        App.logging(logger, httpRequest);
        logger.info(user.toString());
        return repository.save(user);
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public User update(HttpServletRequest httpRequest, @PathVariable Long id, @RequestBody User user) {
        App.logging(logger, httpRequest);
        logger.info(user.toString());
        user.setId(id);
        return repository.save(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Long delete(HttpServletRequest httpRequest, @PathVariable Long id){
        App.logging(logger, httpRequest);
        repository.delete(id);
        return id;
    }
}
