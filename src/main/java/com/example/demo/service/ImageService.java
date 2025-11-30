package com.example.demo.service;

import com.example.demo.entity.Image; // importing Image entity
import com.example.demo.entity.User; // importing User entity
import com.example.demo.entity.Event; // importing Event entity
import com.example.demo.repository.ImageRepository; // importing ImageRepository interface
import org.springframework.stereotype.Service; // importing Service annotation

import java.time.LocalDateTime;
import java.util.List;

@Service // marking this class as a service component in Spring
public class ImageService { // defining ImageService class
    private final ImageRepository repo;

    // constructor injection of ImageRepository
    public ImageService(ImageRepository repo) {
        this.repo = repo;
    }

    // get all the images by calling findAll() method of ImageRepository
    public List<Image> listAll() {
        return repo.findAll();
    }

    // this method returns a list of images filtered by user ID
    public List<Image> listByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    // this method returns a list of images filtered by event ID
    public List<Image> listByEvent(Long eventId) {
        return repo.findByEventId(eventId);
    }

    // this method returns a list of images filtered by their status
    public List<Image> listByStatus(String status) {
        return repo.findByStatus(status);
    }

    // this method creates a new image by saving the provided Image object using the
    // save() method of ImageRepository
    public Image create(Image image) {
        return repo.save(image);
    }

    // this method updates the status of an image identified by its ID
    public Image updateStatus(Long id, String status) {
        Image image = repo.findById(id).orElseThrow();
        image.setStatus(status);
        return repo.save(image);
    }

    public Image createImage(String fileUrl, Long userId, Long eventId) {
        Image image = new Image();
        image.setFileUrl(fileUrl);
        image.setCreatedAt(LocalDateTime.now());

        User user = new User();
        user.setId(userId);
        image.setUser(user);

        if (eventId != null) {
            Event event = new Event();
            event.setId(eventId);
            image.setEvent(event);
        }

        return repo.save(image);
    }

    // this method deletes an image by its ID using the deleteById() method of
    // ImageRepository
    public void delete(Long id) {
        repo.deleteById(id);
    }
}