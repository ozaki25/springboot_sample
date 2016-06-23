package com.example.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3010")
@RestController
@RequestMapping("/statuses")
public class StatusesController {
    private final Log logger = LogFactory.getLog(StatusesController.class);
    @Autowired
    private StatusRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Status> index() {
        List<Status> statuses = repository.findAll();
        logger.info(Status.toString(statuses));
        return statuses;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Status show(@PathVariable Long id) {
        Status s = repository.findById(id);
        logger.info(s.toString());
        return s;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Status create(@RequestBody Status status) {
        Status s = repository.save(status);
        logger.info(s.toString());
        return s;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Status update(@PathVariable Long id, @RequestBody Status status) {
        status.setId(id);
        Status s = repository.save(status);
        logger.info(s.toString());
        return s;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Status delete(@PathVariable Long id){
        Status s = repository.findById(id);
        logger.info(s.toString());
        repository.delete(id);
        return s;
    }
}
