package com.tss.survey.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="trails")
public class Trail {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String city;
    private String state;
    private String difficulty;
    private double distance;
    private int rating;
    private String notes;

    //Constructor
    public Trail() {}

    public Trail(String name, LocalDate date, String city, String state, double distance, int rating, String notes) {
        this.name = name;
        this.date = date;
        this.city = city;
        this.state = state;
        this.distance = distance;
        this.rating = rating;
        this.notes = notes;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "Trail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", distance=" + distance +
                ", rating=" + rating +
                ", notes='" + notes + '\'' +
                '}';
    }
}
