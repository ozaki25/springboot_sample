package com.example.workflow;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories/{categoryId}/receptnists")
public class ReceptnistsController {
    private final Log logger = LogFactory.getLog(ReceptnistsController.class);
    @Autowired
    private ReceptnistRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Receptnist> index(HttpServletRequest httpRequest, @PathVariable Long categoryId) {
        App.logging(logger, httpRequest);
        return categoryRepository.findById(categoryId).getReceptnists();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Receptnist show(HttpServletRequest httpRequest, @PathVariable Long id) {
        App.logging(logger, httpRequest);
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Receptnist create(HttpServletRequest httpRequest, @RequestBody Receptnist receptnist) {
        App.logging(logger, httpRequest);
        logger.info(receptnist.toString());
        return repository.save(receptnist);
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Receptnist update(HttpServletRequest httpRequest, @PathVariable Long id, @RequestBody Receptnist receptnist) {
        App.logging(logger, httpRequest);
        logger.info(receptnist.toString());
        receptnist.setId(id);
        return repository.save(receptnist);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Long delete(HttpServletRequest httpRequest, @PathVariable Long id){
        App.logging(logger, httpRequest);
        repository.delete(id);
        return id;
    }
}
