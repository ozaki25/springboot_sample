package com.example.workflow;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantsController {
    private final Log logger = LogFactory.getLog(ApplicantsController.class);
    @Autowired
    private ApplicantService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Applicant> index(HttpServletRequest httpRequest, @RequestParam MultiValueMap<String, String> query) {
        App.logging(logger, httpRequest);
        String param = query.toSingleValueMap().get("uniq");
        return StringUtils.hasLength(param) ? service.findDistinct(param) : service.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Applicant show(HttpServletRequest httpRequest, @PathVariable Long id) {
        App.logging(logger, httpRequest);
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Applicant create(HttpServletRequest httpRequest, @RequestBody Applicant applicant) {
        App.logging(logger, httpRequest);
        logger.info(applicant.toString());
        return service.save(applicant);

    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Applicant update(HttpServletRequest httpRequest, @PathVariable Long id, @RequestBody Applicant applicant) {
        App.logging(logger, httpRequest);
        logger.info(applicant.toString());
        applicant.setId(id);
        return service.save(applicant);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Long delete(HttpServletRequest httpRequest, @PathVariable Long id){
        App.logging(logger, httpRequest);
        service.delete(id);
        return id;
    }
}
