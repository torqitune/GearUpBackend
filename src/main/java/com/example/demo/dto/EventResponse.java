package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EventResponse {
    private Long id;
    private String title;
    private LocalDate eventDate;
    private String city;
    private String brand;
    private String type;
    private String description;
    private LocalDateTime createdAt;
}