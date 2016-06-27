package com.example.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/requests-test")
public class RequestsConfirmController {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("requests", requestRepository.findAll());
        return "requests/index";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newRequest(Model model) {
        model.addAttribute("statuses", statusRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "requests/new";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("request", requestRepository.findById(id));
        model.addAttribute("statuses", statusRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "requests/edit";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("request", requestRepository.findById(id));
        return "requests/show";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Request request) {
        requestRepository.save(request);
        return "redirect:/requests-test";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute Request request) {
        request.setId(id);
        requestRepository.save(request);
        return "redirect:/requests-test";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id){
        requestRepository.delete(id);
        return "redirect:/requests-test";
    }
}
