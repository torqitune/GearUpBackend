package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;
import com.example.demo.dto.ImageUploadRequest;
import com.example.demo.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController // marking this class as a REST controller
@RequestMapping("/api/images") // base URL for all endpoints in this controller
public class ImageController {

    private final ImageService imageService;

    // this constructor injects the ImageService into the controller
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<Image> List() {
        return imageService.listAll();
    }

    @GetMapping("/user/{userId}")
    public List<Image> findByUserId(@PathVariable Long userId) { // this method handles HTTP GET requests to retrieve
                                                                 // images by user ID
        return imageService.listByUser(userId);
    }

    // creating a post mapping to upload/create a new image
    @PostMapping
    public Image create(@RequestBody ImageUploadRequest request, Authentication authentication) {
        // Get the authenticated user's ID from the JWT token
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long authenticatedUserId = userPrincipal.getId();

        // Use the authenticated user's ID, NOT the one from request body (security!)
        return imageService.createImage(request.getFileUrl(), authenticatedUserId, request.getEventId());
    }
}
