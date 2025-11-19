package com.example.demo.controller;        // this tells java that this class belongs to the controller package


import com.example.demo.entity.Blog;
import com.example.demo.service.BlogService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController         // this annotation marks the class as a RESTful web service controller
@RequestMapping("/api/blogs")   // this annotation maps HTTP requests to handler methods of MVC and REST controllers
public class BlogContoller {

    private final BlogService service;


    // a constructor that takes a BlogService object as a parameter and assigns it to the service field
    public BlogContoller(BlogService service) {
        this.service = service;
    }

    @GetMapping
    public List<Blog> findAll() {      //this method handles HTTP GET requests to retrieve all blog entries
        return service.listAll();
    }

    @GetMapping("/user/{userId}")
    public List<Blog> findByUserId(@PathVariable Long userId) {
        return service.listByUser(userId);
    }

    @GetMapping("/{id}")
    public Blog findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/status/{status}")
    public List<Blog> findByStatus(@PathVariable String status){
        return service.listByStatus(status);
    }

    // creating a post mapping to create a new blog
    @PostMapping
    public Blog create(@RequestBody Blog blog){
        return service.create(blog);
    }
}
