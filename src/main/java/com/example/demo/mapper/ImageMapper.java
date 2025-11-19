package com.example.demo.mapper;

import com.example.demo.dto.CreateImageRequest;
import com.example.demo.dto.ImageResponse;
import com.example.demo.entity.Image;
import com.example.demo.entity.User;
import com.example.demo.entity.Event;

public class ImageMapper {
    // this method converts CreateImageRequest DTO to Image entity so that it can be saved in the database
    public static Image toEntity(CreateImageRequest req, User user, Event event) {
        Image image = new Image();
        image.setUser(user);
        image.setEvent(event);
        image.setFileUrl(req.getFileUrl());
        image.setStatus("PENDING");
        return image;
    }


    // this method converts the Image entity from the database to ImageResponse DTO
    public static ImageResponse toResponse(Image image) {
        ImageResponse res = new ImageResponse();
        res.setId(image.getId());
        res.setUserId(image.getUser().getId());
        res.setEventId(image.getEvent().getId());
        res.setFileUrl(image.getFileUrl());
        res.setStatus(image.getStatus());
        res.setCreatedAt(image.getCreatedAt());
        return res;
    }
}