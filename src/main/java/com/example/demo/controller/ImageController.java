package com.example.demo.controller;

import com.example.demo.entity.Image;
import com.example.demo.service.ImageService;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.security.UserPrincipal;
import java.util.List;
import java.util.Map;

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

    // creating a postmapping for uploading images
    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestBody Map<String, Object> request, Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal(); // getting the user details from
                                                                                     // the authentication object
        String fileUrl = (String) request.get("fileUrl"); // getting the file URL from the request
        Long eventId = request.get("eventId") != null ? Long.parseLong(request.get("eventId").toString()) : null; // getting
                                                                                                                  // the
                                                                                                                  // event
                                                                                                                  // ID
                                                                                                                  // from
                                                                                                                  // the
                                                                                                                  // request

        Image savedImage = imageService.createImage(fileUrl, userPrincipal.getId(), eventId); // calling the createImage
                                                                                              // method of ImageService
                                                                                              // to create a new image

        return ResponseEntity.ok(savedImage); // return with the saved image

    }
}
