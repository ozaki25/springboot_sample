package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3010")
@RestController
@RequestMapping("/requests")
public class RequestsController {
    @Autowired
    private RequestRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Request> index() {
        List<Request> requests = repository.findAll();
        System.out.println(Request.toString(requests));
        return requests;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Request show(@PathVariable Long id) {
        Request r = repository.findById(id);
        System.out.println(r.toString());
        return r;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Request create(@RequestBody Request request) {
        Request r = repository.save(request);
        System.out.println(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}", method = { RequestMethod.PATCH, RequestMethod.PUT })
    public Request update(@PathVariable Long id, @ModelAttribute Request request) {
        request.setId(id);
        Request r = repository.save(request);
        System.out.println(r.toString());
        return r;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Request delete(@PathVariable Long id){
        Request r = repository.findById(id);
        System.out.println(r.toString());
        repository.delete(id);
        return r;
    }
}
