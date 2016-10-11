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
@RequestMapping("requests/{requestId}/histories")
public class HistoriesController {
    private final Log logger = LogFactory.getLog(HistoriesController.class);
    @Autowired
    private HistoryRepository repository;
    @Autowired
    private RequestRepository requestRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<History> index(HttpServletRequest httpRequest, @PathVariable Long requestId) {
        App.logging(logger, httpRequest);
        return requestRepository.findById(requestId).getHistories();

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public History show(HttpServletRequest httpRequest, @PathVariable Long id) {
        App.logging(logger, httpRequest);
        return repository.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public History create(HttpServletRequest httpRequest, @RequestBody History history) {
        App.logging(logger, httpRequest);
        logger.info(history.toString());
        return repository.save(history);
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public History update(HttpServletRequest httpRequest, @PathVariable Long id, @RequestBody History history) {
        App.logging(logger, httpRequest);
        logger.info(history.toString());
        history.setId(id);
        return repository.save(history);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Long delete(HttpServletRequest httpRequest, @PathVariable Long id){
        App.logging(logger, httpRequest);
        repository.delete(id);
        return id;
    }
}
