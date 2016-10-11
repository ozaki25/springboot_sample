package com.example.workflow;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/statuses")
public class StatusesController {
    private final Log logger = LogFactory.getLog(StatusesController.class);
    @Autowired
    private StatusRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Status> index(HttpServletRequest httpRequest) {
        App.logging(logger, httpRequest);
        return repository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Status show(HttpServletRequest httpRequest, @PathVariable Long id) {
        App.logging(logger, httpRequest);
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Status create(HttpServletRequest httpRequest, @RequestBody Status status) {
        App.logging(logger, httpRequest);
        logger.info(status.toString());
        return repository.save(status);
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Status update(HttpServletRequest httpRequest, @PathVariable Long id, @RequestBody Status status) {
        App.logging(logger, httpRequest);
        logger.info(status.toString());
        status.setId(id);
        return repository.save(status);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Long delete(HttpServletRequest httpRequest, @PathVariable Long id){
        App.logging(logger, httpRequest);
        repository.delete(id);
        return id;
    }
}
