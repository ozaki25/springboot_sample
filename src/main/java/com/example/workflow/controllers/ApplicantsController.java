package com.example.workflow;

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
    public List<Applicant> index(@RequestParam MultiValueMap<String, String> query) {
        logger.info("query: " + query);
        String param = query.toSingleValueMap().get("uniq");
        List<Applicant> applicants = new ArrayList<Applicant>();
        if(StringUtils.hasLength(param)) {
            applicants = service.findDistinct(param);
        } else {
            applicants = service.findAll();
            logger.info(Applicant.toString(applicants));
        }
        return applicants;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Applicant show(@PathVariable Long id) {
        Applicant u = service.findById(id);
        logger.info(u.toString());
        return u;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Applicant create(@RequestBody Applicant applicant) {
        Applicant u = service.save(applicant);
        logger.info(u.toString());
        return u;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Applicant update(@PathVariable Long id, @RequestBody Applicant applicant) {
        applicant.setId(id);
        Applicant u = service.save(applicant);
        logger.info(u.toString());
        return u;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Applicant delete(@PathVariable Long id){
        Applicant u = service.findById(id);
        logger.info(u.toString());
        service.delete(id);
        return u;
    }
}
