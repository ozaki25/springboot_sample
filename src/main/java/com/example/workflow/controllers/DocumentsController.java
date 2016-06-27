package com.example.workflow;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/documents")
public class DocumentsController {
    private final Log logger = LogFactory.getLog(DocumentsController.class);
    @Autowired
    private DocumentRepository repository;

    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    public ResponseEntity<byte[]> show(@PathVariable Long id) throws IOException {
        Document d = repository.findById(id);
        logger.info(d.toString());

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/force-download; charset=UTF-8");
        header.setContentDispositionFormData("filename", d.getFilename());

        return new ResponseEntity<byte[]>(d.getFile(), header, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Document create(@RequestParam("file") MultipartFile file) throws IOException {
        Document document = new Document(file.getOriginalFilename(), file.getBytes());
        Document d = repository.save(document);
        logger.info(d.toString());
        return d;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Document delete(@PathVariable Long id){
        Document d = repository.findById(id);
        logger.info(d.toString());
        repository.delete(id);
        return d;
    }
}
