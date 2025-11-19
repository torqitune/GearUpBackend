package com.example.demo.dto;


import lombok.Data;
import java.time.LocalDateTime;


@Data
public class ImageResponse {
    private Long id;
    private Long userId;
    private Long eventId;
    private String fileUrl;
    private String status;
    private LocalDateTime createdAt;
}