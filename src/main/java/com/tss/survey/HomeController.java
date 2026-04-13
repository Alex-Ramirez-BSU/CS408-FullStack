package com.tss.survey;

import com.tss.survey.model.Trail;
import com.tss.survey.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
public class HomeController {

    //Repository Initialized
    @Autowired
    private TrailRepository trailRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Trail Tracker");
        model.addAttribute("message", "This is the homepage.");
        return "index";
    }

    @GetMapping("/trails")
    public String Trails(Model model){
        model.addAttribute("title", "Trails Explored");
        model.addAttribute("trails", trailRepository.findAll());
        return "trails";
    }

    @GetMapping("/trails/{id}")
    public String Trail(@PathVariable("id") int id, Model model){
        Trail trail = trailRepository.findById(id).orElse(null);
        model.addAttribute("title", "Trail Explored");
        model.addAttribute("trail", trail);
        return "details";
    }

    @GetMapping("/trails/edit/{id}")
    public String editTrail(@PathVariable("id") int id, Model model){
        Trail trail = trailRepository.findById(id).orElse(null);
        model.addAttribute("title", "Edit Trail");
        model.addAttribute("trail", trail);
        return "add";
    }

    @PostMapping("/trails/delete/{id}")
    public String deleteTrail(@PathVariable("id") int id) {
        trailRepository.deleteById(id);
        return "redirect:/trails";
    }

    @GetMapping("/add")
    public String addTrail(Model model){
        model.addAttribute("title", "Add Trail");
        model.addAttribute("message", "This is where you add trails.");
        model.addAttribute("trail", new Trail());
        return "add";
    }

    @PostMapping("/add-trail")
    public String addTrail(@ModelAttribute Trail trail){
        trailRepository.save(trail);
        return "redirect:/trails";
    }

    @GetMapping("/stats")
    public String stats(Model model){
        model.addAttribute("title", "Trail Stats");
        model.addAttribute("message", "This is the stats list page.");
        return "index";
    }

//    @GetMapping("/test")
//    @ResponseBody
//    public String test() {
//        return trailRepository.findAll().toString();
//    }
}
