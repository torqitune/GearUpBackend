package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     // declaring this class as a REST controller
@RequestMapping("/api/users")       // base URL for all endpoints in this controller
public class UserController {
    private final UserService service;

    public UserController(UserService service) {        // constructor injection of UserService
        this.service = service;
    }

    @GetMapping     // mapping HTTP GET requests to this method
    public List<User> list() {      // method to list all users
        return service.listAll();
    }

    @GetMapping("/{id}")        // mapping HTTP GET requests with an id path variable to this method
    public User getById(@PathVariable Long id) {        // method to get a user by id
        return service.getById(id);
    }

    @GetMapping("/email/{email}")       // mapping HTTP GET requests with an email path variable to this method
    public User getByEmail(@PathVariable String email) {        // method to get a user by email
        return service.getByEmail(email);
    }

    @PostMapping        // mapping HTTP POST requests to this method
    public User create(@RequestBody User user) {        // method to create a new user
        return service.create(user);
    }

    @DeleteMapping("/{id}") // mapping HTTP DELETE requests with an id path variable to this method
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}