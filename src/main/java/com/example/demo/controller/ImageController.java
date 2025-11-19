package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController     // marking this class as a REST controller
@RequestMapping("/api/images")       // base URL for all endpoints in this controller
public class ImageController {

    private final ImageService imageService;

    // this constructor injects the ImageService into the controller
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<Image> List(){
        return imageService.listAll();
    }

    @GetMapping("/user/{userId}")
    public List<Image> findByUserId(@PathVariable Long userId) {        // this method handles HTTP GET requests to retrieve images by user ID
        return imageService.listByUser(userId);
    }
}
