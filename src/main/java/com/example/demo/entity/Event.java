package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title" , length=255 , nullable=false)
    private String title;

    @Column(name="event_date"  , nullable=false)
    private LocalDate eventDate;

    @Column(name="city" , length=100)
    private String city;

    @Column(name="brand" ,length=100)
    private String brand;

    @Column(name="type" , length=50)
    private String type;

    @Column(name="description" , columnDefinition = "TEXT")
    private String description;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    // --- Getters and Setters ---
    public Long getId() { return id; }      // this method returns the value of the id field
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }      // this method returns the value of the title field
    public void setTitle(String title) { this.title = title; }      // this method sets the value of the title field

    public LocalDate getEventDate() { return eventDate; }       // this method returns the value of the eventDate field
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }       // this method sets the value of the eventDate field

    public String getCity() { return city; }       // this method returns the value of the city field
    public void setCity(String city) { this.city = city; }

    public String getBrand() { return brand; }      // this method returns the value of the brand field
    public void setBrand(String brand) { this.brand = brand; }

    public String getType() { return type; }        // this method returns the value of the type field
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }      //this method returns the value of the description field
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }       // this method returns the value of the createdAt field
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}