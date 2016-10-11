package com.example.workflow;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentsController {
    private final Log logger = LogFactory.getLog(DocumentsController.class);
    @Autowired
    private DocumentRepository repository;
    @Autowired
    private RequestRepository requestRepository;

    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    public ResponseEntity<byte[]> show(HttpServletRequest httpRequest, @PathVariable Long id) throws IOException {
        App.logging(logger, httpRequest);
        Document d = repository.findById(id);

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/force-download; charset=UTF-8");
        header.setContentDispositionFormData("filename", d.getFilename());

        return new ResponseEntity<byte[]>(d.getFile(), header, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Document create(HttpServletRequest httpRequest, @RequestParam("request") String requestId, @RequestParam("file") MultipartFile file) throws IOException {
        App.logging(logger, httpRequest);
        Request request = requestId.equals("") ? null : requestRepository.findById(Long.parseLong(requestId));
        Document document = new Document(file.getOriginalFilename(), file.getBytes(), request);
        logger.info(document.toString());
        return repository.save(document);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Long delete(HttpServletRequest httpRequest, @PathVariable Long id){
        App.logging(logger, httpRequest);
        repository.delete(id);
        return id;
    }
}
