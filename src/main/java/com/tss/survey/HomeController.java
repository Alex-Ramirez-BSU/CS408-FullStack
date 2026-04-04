package com.tss.survey;

import com.tss.survey.model.Trail;
import com.tss.survey.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;


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

    @GetMapping("/add")
    public String addTrail(Model model){
        model.addAttribute("title", "Add Trail");
        model.addAttribute("message", "This is where you add trails.");
        return "add";
    }

    @PostMapping("/add-trail")
    public String addTrail(Trail trail){
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
