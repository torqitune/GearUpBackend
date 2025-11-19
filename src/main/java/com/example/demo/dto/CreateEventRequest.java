package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data           // marking this class as a Data Transfer Object , a DTO is used to transfer data between processes.
public class CreateEventRequest {
    @NotBlank
    private String title;       // Event title must not be blank

    @NotNull
    private LocalDate eventDate;        // Event date must not be null

    private String city;
    private String brand;
    private String type;
    private String description;
}