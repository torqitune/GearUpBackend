package com.example.demo.dto;
import lombok.Data;
import java.time.LocalDateTime;


@Data
public class BlogResponse {
    private Long id;
    private Long userId;
    private Long eventId;
    private String content;
    private String status;
    private LocalDateTime createdAt;
}