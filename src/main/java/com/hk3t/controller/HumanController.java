package com.hk3t.controller;

import com.hk3t.model.Human;
import com.hk3t.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class HumanController {
    @Autowired
    private HumanService humanService;

    @GetMapping("/")
    public ModelAndView homepage(@RequestParam("name") Optional <String> name, Pageable pageable) {
        Page <Human> humans;
        if (name.isPresent()) {
            humans = humanService.findAllByNameContaining(name.get(), pageable);
        } else {
            humans = humanService.findAll(new PageRequest(pageable.getPageNumber(), 10));
        }
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("human", humans);
        return modelAndView;
    }

    @GetMapping("/thing")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("human", new Human());
        return modelAndView;
    }

    @PostMapping("/thing")
    public String saveHuman(@ModelAttribute("human") Human human, RedirectAttributes redirectAttributes) {
        humanService.save(human);
        redirectAttributes.addFlashAttribute("message", "New human created successfully");
        return "redirect:/";
    }

    @GetMapping("/thing/view/{id}")
    public ModelAndView viewHuman(@PathVariable Long id) {
        Human human = humanService.findById(id);
        ModelAndView modelAndView = new ModelAndView("view");
        modelAndView.addObject("human", human);
        return modelAndView;
    }

    @GetMapping("/thing/remove/{id}")
    public String removeHuman(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Human human = humanService.findById(id);
        humanService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Removed human: " + human.getName());
        return "redirect:/";
    }
}
