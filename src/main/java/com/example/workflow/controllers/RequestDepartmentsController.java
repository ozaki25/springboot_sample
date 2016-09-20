package com.example.workflow;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request-departments")
public class RequestDepartmentsController {
    @Autowired
    private RequestDepartmentRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<RequestDepartment> years() {
        return repository.findAll();
    }
}
