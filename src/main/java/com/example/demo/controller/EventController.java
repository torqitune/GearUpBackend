// a controller is used for handling HTTP requests related to Event entities


package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     // marking this class as a REST controller to handle HTTP requests
@RequestMapping("/api/events")      // this defines the base URL for all endpoints in this controller
public class EventController {
    private final EventService service;

    public EventController(EventService service) {      // here we are injecting the EventService into the controller using constructor injection
        this.service = service;         // controlle depends on the service layer to perform business logic , so instead of creating it manually using new keyword we let spring inject it for us
    }

    @GetMapping         // mapping HTTP GET requests to this method
    public List<Event> list() {
        return service.listAll();       // calling the service layer to get all events
    }

    @PostMapping        // mapping HTTP POST requests to this method
    public Event create(@RequestBody Event e) {
        return service.create(e);       // calling the service layer to create a new event
    }


    @GetMapping("/{id}")
    public Event getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}