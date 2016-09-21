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
@RequestMapping("requests/{requestId}/histories")
public class HistoriesController {
    private final Log logger = LogFactory.getLog(HistoriesController.class);
    @Autowired
    private HistoryRepository repository;
    @Autowired
    private RequestRepository requestRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<History> index(@PathVariable Long requestId) {
        Request r = requestRepository.findById(requestId);
        List<History> histories = r.getHistories();
        logger.info(History.toString(histories));
        return histories;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public History show(@PathVariable Long id) {
        History h = repository.findById(id);
        logger.info(h.toString());
        return h;
    }

    @RequestMapping(method = RequestMethod.POST)
    public History create(@RequestBody History history) {
        History h = repository.save(history);
        logger.info(h.toString());
        return h;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public History update(@PathVariable Long id, @RequestBody History history) {
        history.setId(id);
        History h = repository.save(history);
        logger.info(h.toString());
        return h;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public History delete(@PathVariable Long id){
        History h = repository.findById(id);
        logger.info(h.toString());
        repository.delete(id);
        return h;
    }
}
