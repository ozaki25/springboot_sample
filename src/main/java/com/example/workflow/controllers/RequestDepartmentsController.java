package com.example.workflow;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request-departments")
public class RequestDepartmentsController {
    private final Log logger = LogFactory.getLog(RequestDepartmentsController.class);
    @Autowired
    private RequestDepartmentRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<RequestDepartment> index(HttpServletRequest httpRequest) {
        App.logging(logger, httpRequest);
        return repository.findAll();
    }
}
