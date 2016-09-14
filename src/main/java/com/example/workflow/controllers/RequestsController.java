package com.example.workflow;

import java.util.List;
import java.util.ArrayList;
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
    public Request create(@RequestBody Request request) {
        List<Document> documents = request.getDocuments();
        request.setDocuments(new ArrayList<Document>());
        Request r = requestService.save(request);
        for(Document d : documents) {
            d.setRequest(r);
            documentRepository.save(d);
        }
        for(Document d : documentRepository.findByRequestIdIsNull()) documentRepository.delete(d);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Request update(@PathVariable Long id, @RequestBody Request request) {
        request.setId(id);
        Request r = requestService.save(request);

        List<Document> inputDocuments = r.getDocuments();
        List<Document> savedDocuments = documentRepository.findByRequestId(request.getId());
        List<Document> mergedDocuments = new ArrayList<Document>();
        mergedDocuments.addAll(inputDocuments);
        mergedDocuments.addAll(savedDocuments);
        for(Document d : mergedDocuments) {
            boolean input = inputDocuments.contains(d);
            boolean saved = savedDocuments.contains(d);
            if(input && !saved) {
                d.setRequest(r);
                documentRepository.save(d);
            } else if(!input && saved) {
                documentRepository.delete(d);
            }
        }
        for(Document d : documentRepository.findByRequestIdIsNull()) documentRepository.delete(d);
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
