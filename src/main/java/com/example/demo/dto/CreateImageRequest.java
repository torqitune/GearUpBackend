package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateImageRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long eventId;

    @NotBlank
    private String fileUrl;
}