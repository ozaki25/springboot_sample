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
@RequestMapping("/categories")
public class CategoriesController {
    private final Log logger = LogFactory.getLog(CategoriesController.class);
    @Autowired
    private CategoryRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> index(HttpServletRequest httpRequest) {
        App.logging(logger, httpRequest);
        return repository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Category show(HttpServletRequest httpRequest, @PathVariable Long id) {
        App.logging(logger, httpRequest);
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Category create(HttpServletRequest httpRequest, @RequestBody Category category) {
        App.logging(logger, httpRequest);
        logger.info(category.toString());
        return repository.save(category);
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Category update(HttpServletRequest httpRequest, @PathVariable Long id, @RequestBody Category category) {
        App.logging(logger, httpRequest);
        logger.info(category.toString());
        category.setId(id);
        return repository.save(category);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Long delete(HttpServletRequest httpRequest, @PathVariable Long id){
        App.logging(logger, httpRequest);
        repository.delete(id);
        return id;
    }
}
