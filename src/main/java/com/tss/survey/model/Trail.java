package com.tss.survey.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Entity representing a Trail entry in the application.
 * Maps to the "trails" table in the database.
 */
@Entity
@Table(name ="trails")
public class Trail {

    /** Primary key (auto-incremented) */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    /** Name of the trail */
    private String name;

    /** Date the trail was explored (formatted for form input) */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    /** City where the trail is located */
    private String city;

    /** State where the trail is located */
    private String state;

    /** Difficulty level (user-defined, optional/opinion-based) */
    private String difficulty;

    /** Distance of the trail (in miles) */
    private double distance;

    /** Rating of the trail (typically 0–5) */
    private int rating;

    /** Additional notes about the trail */
    private String notes;

    /** Default constructor required by JPA */
    public Trail() {}

    /**
     * Convenience constructor for creating a Trail (excluding ID).
     */
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

    /** @return trail ID */
    public Integer getId() { return id; }

    /** @param id trail ID */
    public void setId(Integer id) { this.id = id; }

    /** @return trail name */
    public String getName() { return name; }

    /** @param name trail name */
    public void setName(String name) { this.name = name; }

    /** @return trail date */
    public LocalDate getDate() { return date; }

    /** @param date trail date */
    public void setDate(LocalDate date) { this.date = date; }

    /** @return city */
    public String getCity() { return city; }

    /** @param city trail city */
    public void setCity(String city) { this.city = city; }

    /** @return state */
    public String getState() { return state; }

    /** @param state trail state */
    public void setState(String state) { this.state = state; }

    /** @return difficulty level */
    public String getDifficulty() { return difficulty; }

    /** @param difficulty difficulty level */
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    /** @return distance in miles */
    public Double getDistance() { return distance; }

    /** @param distance trail distance */
    public void setDistance(Double distance) { this.distance = distance; }

    /** @return rating (0–5) */
    public Integer getRating() { return rating; }

    /** @param rating trail rating */
    public void setRating(Integer rating) { this.rating = rating; }

    /** @return notes */
    public String getNotes() { return notes; }

    /** @param notes trail notes */
    public void setNotes(String notes) { this.notes = notes; }

    /**
     * String representation of the Trail object (useful for debugging/logging)
     */
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