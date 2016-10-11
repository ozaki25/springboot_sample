package com.example.workflow;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request-numbers")
public class RequestNumbersController {
    private final Log logger = LogFactory.getLog(RequestNumbersController.class);
    @Autowired
    private RequestNumberRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<RequestNumber> years(HttpServletRequest httpRequest) {
        App.logging(logger, httpRequest);
        return repository.findAll();
    }
}
