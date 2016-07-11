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
@RequestMapping("/categories-test")
public class CategoriesConfirmController {
    @Autowired
    private CategoryRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("categories", repository.findAll());
        return "categories/index";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newCategory() {
        return "categories/new";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("category", repository.findById(id));
        return "categories/edit";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("category", repository.findById(id));
        return "categories/show";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Category category) {
        repository.save(category);
        return "redirect:/categories";
    }

    @RequestMapping(value = "{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute Category category) {
        category.setId(id);
        repository.save(category);
        return "redirect:/categories";
    }

    @RequestMapping(value = "{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable Long id){
        repository.delete(id);
        return "redirect:/categories";
    }
}
