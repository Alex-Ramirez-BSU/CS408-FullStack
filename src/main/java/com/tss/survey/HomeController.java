package com.tss.survey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Hello World");
        model.addAttribute("message", "This is a template with bootstrap.");
        return "index";
    }
}
