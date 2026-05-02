package com.tss.survey;

import com.tss.survey.model.Trail;
import com.tss.survey.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;

/**
 * Controller for handling page routes and trail-related actions.
 */
@Controller
public class HomeController {

    /** Repository used to access Trail data from the database */
    @Autowired
    private TrailRepository trailRepository;

    /** Displays the homepage */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Trail Tracker");
        model.addAttribute("message", "This is the homepage.");
        return "index";
    }

    /** Displays all trails and applies optional filters */
    @GetMapping("/trails")
    public String Trails(
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) String name,
            Model model) {

        List<Trail> trails = trailRepository.findAll().stream()
                .filter(t -> difficulty == null || difficulty.isBlank()
                        || difficulty.equalsIgnoreCase(t.getDifficulty()))
                .filter(t -> rating == null || Objects.equals(t.getRating(), rating))
                .filter(t -> name == null || name.isBlank()
                        || t.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();

        model.addAttribute("title", "Trails Explored");
        model.addAttribute("trails", trails);
        model.addAttribute("difficulty", difficulty);
        model.addAttribute("rating", rating);
        model.addAttribute("name", name);

        return "trails";
    }

    /** Displays details for a single trail by ID */
    @GetMapping("/trails/{id}")
    public String Trail(@PathVariable("id") int id, Model model){
        Trail trail = trailRepository.findById(id).orElse(null);
        model.addAttribute("title", "Trail Explored");
        model.addAttribute("trail", trail);
        return "details";
    }

    /** Loads an existing trail into the add/edit form */
    @GetMapping("/trails/edit/{id}")
    public String editTrail(@PathVariable("id") int id, Model model){
        Trail trail = trailRepository.findById(id).orElse(null);
        model.addAttribute("title", "Edit Trail");
        model.addAttribute("trail", trail);
        return "add";
    }

    /** Deletes a trail by ID and redirects back to the trail list */
    @PostMapping("/trails/delete/{id}")
    public String deleteTrail(@PathVariable("id") int id) {
        trailRepository.deleteById(id);
        return "redirect:/trails";
    }

    /** Displays a blank form for adding a new trail */
    @GetMapping("/add")
    public String addTrail(Model model){
        model.addAttribute("title", "Add Trail");
        model.addAttribute("trail", new Trail());
        return "add";
    }

    /** Saves a new or edited trail */
    @PostMapping("/add-trail")
    public String addTrail(@ModelAttribute Trail trail){
        trailRepository.save(trail);
        return "redirect:/trails";
    }

    /** Calculates and displays trail statistics */
    @GetMapping("/stats")
    public String stats(Model model){
        // Loading Repo
        List<Trail> trails = trailRepository.findAll();

        // Stats
        long totalTrails = trailRepository.count();
        double totalDistance = trails.stream().mapToDouble(Trail::getDistance).sum();
        double averageRating =  trails.stream().mapToDouble(Trail::getRating).average().orElse(0.0);
        int easyTrails = (int) trails.stream().filter(t -> "Easy".equalsIgnoreCase(t.getDifficulty())).count();
        int mediumTrails = (int)trails.stream().filter(t -> "Medium".equalsIgnoreCase(t.getDifficulty())).count();
        int hardTrails = (int) trails.stream().filter(t -> "Hard".equalsIgnoreCase(t.getDifficulty())).count();
        double longestTrail = trails.stream().mapToDouble(Trail::getDistance).max().orElse(0.0);

        int[] trailsPerMonth = new int[12];

        for (Trail trail : trails) {
            if (trail.getDate() != null) {
                int monthIndex = trail.getDate().getMonthValue() -1;
                trailsPerMonth[monthIndex]++;
            }
        }

        // Adding Attributes
        model.addAttribute("title", "Trail Statistics");
        model.addAttribute("totalTrails", totalTrails);
        model.addAttribute("totalDistance", totalDistance);
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("easyTrails", easyTrails);
        model.addAttribute("mediumTrails", mediumTrails);
        model.addAttribute("hardTrails", hardTrails);
        model.addAttribute("longestTrail", longestTrail);
        model.addAttribute("trailsPerMonth", trailsPerMonth);

        return "stats";
    }
}
