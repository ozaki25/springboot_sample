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
@RequestMapping("/categories")
public class CategoriesController {
    private final Log logger = LogFactory.getLog(CategoriesController.class);
    @Autowired
    private CategoryRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> index() {
        List<Category> categories = repository.findAll();
        logger.info(Category.toString(categories));
        return categories;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Category show(@PathVariable Long id) {
        Category c = repository.findById(id);
        logger.info(c.toString());
        return c;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Category create(@RequestBody Category category) {
        Category c = repository.save(category);
        logger.info(c.toString());
        return c;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        Category c = repository.save(category);
        logger.info(c.toString());
        return c;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Category delete(@PathVariable Long id){
        Category c = repository.findById(id);
        logger.info(c.toString());
        repository.delete(id);
        return c;
    }
}
