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
@RequestMapping("/statuses-test")
public class StatusesConfirmController {
    @Autowired
    private StatusRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("statuses", repository.findAll());
        return "statuses/index";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newStatus() {
        return "statuses/new";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("status", repository.findById(id));
        return "statuses/edit";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("status", repository.findById(id));
        return "statuses/show";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Status status) {
        repository.save(status);
        return "redirect:/statuses-dev";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute Status status) {
        status.setId(id);
        repository.save(status);
        return "redirect:/statuses-dev";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id){
        repository.delete(id);
        return "redirect:/statuses-dev";
    }
}
