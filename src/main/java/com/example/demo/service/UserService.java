package com.example.demo.service; // defining the package name

import com.example.demo.entity.User; // importing User entity class
import com.example.demo.repository.UserRepository; // importing UserRepository interface
import org.springframework.stereotype.Service; // importing Service annotation from Spring Framework
import java.util.List; // importing List from java.util package

@Service // marking this class as a service component in Spring
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // this method returns a list of all users by calling the findAll() method of
    // UserRepository
    public List<User> listAll() {
        return repo.findAll();
    }

    // this method creates a new user by saving the provided User object using the
    // save() method of UserRepository
    public User create(User u) {
        return repo.save(u);
    }

    // this method retrieves a User by its ID using the findById() method of
    // UserRepository
    public User getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // this method retrieves a User by its email using the custom findByEmail()
    // method of UserRepository
    public User getByEmail(String email) {
        return repo.findByEmail(email);
    }

    // this method deletes a User by its ID using the deleteById() method of
    // UserRepository
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
