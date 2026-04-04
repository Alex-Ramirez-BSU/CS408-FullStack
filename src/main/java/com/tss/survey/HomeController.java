package com.tss.survey;

import com.tss.survey.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Trail Tracker");
        model.addAttribute("message", "This is the homepage.");
        return "index";
    }

    @RequestMapping("/trails")
    public String Trails(Model model){
        model.addAttribute("title", "Trails Explored");
        model.addAttribute("message", "This is the trails list page.");
        return "trails";
    }

    @RequestMapping("/add")
    public String addTrail(Model model){
        model.addAttribute("title", "Add Trail");
        model.addAttribute("message", "This is where you add trails.");
        return "index";
    }

    @RequestMapping("/stats")
    public String stats(Model model){
        model.addAttribute("title", "Trail Stats");
        model.addAttribute("message", "This is the stats list page.");
        return "index";
    }

    @Autowired
    private TrailRepository trailRepository;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return trailRepository.findAll().toString();
    }
}
