package com.example.demo.service;       // this is the service layer where we implement business logic

import com.example.demo.entity.Event;   //
import com.example.demo.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service        // service annotation to mark this class as a service component in spring ,i.e we are saying that this class provides business logic , also this keeps it ready in the memory , it lets us inject it anywhere using @Autowired
public class EventService {
    private final EventRepository repo; // final bcoz , once initialized in the constructor it should not be changed

    public EventService(EventRepository repo) {     // constructor injection of the repository , i.e injecting the repository (repo) into the service class (eventService)
        this.repo = repo;
    }

    public List<Event> listAll() {      // method to list all events
        return repo.findAll();
    }

    public Event create(Event e) {  // method to create a new event , i.e take an event object and save it to the database , note : upsert operation
        return repo.save(e);
    }

    public Event getById(Long id) {      // method to get an event by its id
        return repo.findById(id).orElseThrow();
    }

    public Event delete(Long id) {      // method to delete an event by its id
        Event event = repo.findById(id).orElseThrow();
        repo.deleteById(id);
        return event;
    }
}