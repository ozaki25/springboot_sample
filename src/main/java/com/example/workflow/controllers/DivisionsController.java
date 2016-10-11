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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories/{categoryId}/divisions")
public class DivisionsController {
    private final Log logger = LogFactory.getLog(DivisionsController.class);
    @Autowired
    private DivisionRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Division> divisions(HttpServletRequest httpRequest, @PathVariable Long categoryId) {
        App.logging(logger, httpRequest);
        return categoryRepository.findById(categoryId).getDivisions();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Division show(HttpServletRequest httpRequest, @PathVariable Long id) {
        App.logging(logger, httpRequest);
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Division create(HttpServletRequest httpRequest, @RequestBody Division division) {
        App.logging(logger, httpRequest);
        logger.info(division.toString());
        return repository.save(division);
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Division update(HttpServletRequest httpRequest, @PathVariable Long id, @RequestBody Division division) {
        App.logging(logger, httpRequest);
        logger.info(division.toString());
        division.setId(id);
        return repository.save(division);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Long delete(HttpServletRequest httpRequest, @PathVariable Long id){
        App.logging(logger, httpRequest);
        repository.delete(id);
        return id;
    }
}
