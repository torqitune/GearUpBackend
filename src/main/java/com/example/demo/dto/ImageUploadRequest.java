package com.example.demo.dto;

import lombok.Data;

@Data
public class ImageUploadRequest {
    private String fileUrl;
    private Long userId;
    private Long eventId;
}
