package com.example.demo.service; // this defines where the class lives

import com.example.demo.entity.Blog; // import the Blogs entity
import com.example.demo.repository.BlogRepository; // import the BlogRepository
import org.springframework.stereotype.Service;

import java.util.List;

@Service // marking this class a service component in Spring
public class BlogService {
    private final BlogRepository repo; // final bcoz once initialized in the constructor it should not be changed

    // this is the constructor for BlogService class which takes BlogRepository as a
    // parameter and initializes the repo field
    public BlogService(BlogRepository repo) {
        this.repo = repo;
    }

    // this method returns a list of all blogs by calling the findAll() method of
    // BlogRepository
    public List<Blog> listAll() {
        return repo.findAll();
    }

    // this method creates a new blog by saving the provided Blogs object using the
    // save() method of BlogRepository
    public List<Blog> listByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    // this method returns a list of blogs filtered by their event id
    public List<Blog> listByEvent(Long eventId) {
        return repo.findByEventId(eventId);
    }

    // this method finds blogs by their status
    public List<Blog> listByStatus(String status) {
        return repo.findByStatus(status);
    }

    // this method creates a new blog by saving the provided Blogs object using the
    // save() method of BlogRepository
    public Blog create(Blog b) {
        return repo.save(b);
    }

    public Blog updateStatus(Long id, String status) {
        Blog blog = repo.findById(id).orElseThrow(); // this line fetches the blog with the given id from the
                                                     // repository, or throws an exception if not found
        blog.setStatus(status); // this line sets the status of the fetched blog to the provided status
        return repo.save(blog); // this line saves the updated blog back to the repository and returns it
    }

    public Blog findById(Long id) {
        return repo.findById(id).orElseThrow(); //
    }

    // this method deletes a blog by its ID using the deleteById() method of
    // BlogRepository
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
