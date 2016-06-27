package com.example.workflow;

import java.io.IOException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/requests")
public class RequestsController {
    private final Log logger = LogFactory.getLog(RequestsController.class);
    @Autowired
    private RequestRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Request> index() {
        List<Request> requests = repository.findAll();
        logger.info(Request.toString(requests));
        return requests;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Request show(@PathVariable Long id) {
        Request r = repository.findById(id);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}/download", method=RequestMethod.GET)
    public ResponseEntity<byte[]> download(@PathVariable Long id) throws IOException {
        Request r = repository.findById(id);
        logger.info(r.toString());

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/force-download; charset=UTF-8");
        header.setContentDispositionFormData("filename", r.getFilename());

        return new ResponseEntity<byte[]>(r.getFile(), header, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Request create(@RequestParam("file") MultipartFile file,
                          @RequestParam("title") String title,
                          @RequestParam("content") String content,
                          @RequestParam("status") Status status,
                          @RequestParam("user") User user) throws IOException {
        Request request = new Request(title, content, file.getOriginalFilename(), file.getBytes(), status, user);
        Request r = repository.save(request);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Request update(@PathVariable Long id, @RequestBody Request request) {
        request.setId(id);
        Request r = repository.save(request);
        logger.info(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Request delete(@PathVariable Long id){
        Request r = repository.findById(id);
        logger.info(r.toString());
        repository.delete(id);
        return r;
    }
}
