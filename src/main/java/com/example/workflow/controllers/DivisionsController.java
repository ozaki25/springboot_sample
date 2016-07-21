package com.example.workflow;

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
    public List<Division> divisions(@PathVariable Long categoryId) {
        Category c = categoryRepository.findById(categoryId);
        List<Division> divisions = c.getDivisions();
        logger.info(Division.toString(divisions));
        return divisions;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Division show(@PathVariable Long id) {
        Division d = repository.findById(id);
        logger.info(d.toString());
        return d;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Division create(@RequestBody Division division) {
        Division d = repository.save(division);
        logger.info(d.toString());
        return d;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Division update(@PathVariable Long id, @RequestBody Division division) {
        division.setId(id);
        Division d = repository.save(division);
        logger.info(d.toString());
        return d;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Division delete(@PathVariable Long id){
        Division d = repository.findById(id);
        logger.info(d.toString());
        repository.delete(id);
        return d;
    }
}
