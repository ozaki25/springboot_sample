package com.example.workflow;

import java.util.List;
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
    public List<Receptnist> receptnists(@PathVariable Long categoryId) {
        Category r = categoryRepository.findById(categoryId);
        List<Receptnist> receptnists = r.getReceptnists();
        logger.info(Receptnist.toString(receptnists));
        return receptnists;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Receptnist show(@PathVariable Long id) {
        Receptnist r = repository.findById(id);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Receptnist create(@RequestBody Receptnist receptnist) {
        Receptnist r = repository.save(receptnist);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Receptnist update(@PathVariable Long id, @RequestBody Receptnist receptnist) {
        receptnist.setId(id);
        Receptnist r = repository.save(receptnist);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Receptnist delete(@PathVariable Long id){
        Receptnist r = repository.findById(id);
        logger.info(r.toString());
        repository.delete(id);
        return r;
    }
}
