package com.example.workflow;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requests")
public class RequestsController {
    private final Log logger = LogFactory.getLog(RequestsController.class);
    @Autowired
    private RequestService requestService;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Request> index(@RequestParam MultiValueMap<String, String> query) {
        RequestSearch requestSearch = new RequestSearch(query);
        List<Request> requests = requestService.search(requestSearch);
        logger.info(Request.toString(requests));
        return requests;
    }

    @RequestMapping(value = "page/{page}", method = RequestMethod.GET)
    public Page page(@PathVariable int page, @RequestParam MultiValueMap<String, String> query) {
        RequestSearch requestSearch = new RequestSearch(query);
        int totalPage = requestService.getTotalPage(requestSearch);
        return new Page(page, totalPage);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Request show(@PathVariable Long id) {
        Request r = requestService.findById(id);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Request create(HttpServletRequest httpRequest, @RequestBody Request request) {
        String uid = httpRequest.getSession().getAttribute("uid").toString();
        String name = httpRequest.getSession().getAttribute("name").toString();
        String team = httpRequest.getSession().getAttribute("team").toString();
        Request r = requestService.save(request, uid, name, team);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Request update(HttpServletRequest httpRequest, @PathVariable Long id, @RequestBody Request request) {
        if(!request.getUpdatedDate().equals(requestService.findById(id).getUpdatedDate())) throw new IllegalArgumentException("他のユーザによって更新されました。");
        String uid = httpRequest.getSession().getAttribute("uid").toString();
        String name = httpRequest.getSession().getAttribute("name").toString();
        String team = httpRequest.getSession().getAttribute("team").toString();
        request.setId(id);
        Request r = requestService.save(request, uid, name, team);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Request delete(@PathVariable Long id){
        Request r = requestService.findById(id);
        logger.info(r.toString());
        requestService.delete(id);
        return r;
    }
}
