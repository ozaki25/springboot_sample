package com.example.workflow;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request-numbers")
public class RequestNumbersController {
    @Autowired
    private RequestNumberRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<RequestNumber> years() {
        return repository.findAll();
    }
}
