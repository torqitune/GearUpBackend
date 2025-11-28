package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController // this annotation marks this is a rest controller , i.e it handles HTTP
                // requests and returns HTTP responses
@RequestMapping("/api/auth") // this annotation maps the requests to the controller
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    // constuctor injection
    public AuthController(
            UserService userService,
            JwtTokenProvider jwtTokenProvider,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) { // using a generic type
                                                                                  // Map<String,String> to accept the
                                                                                  // request body to read name , email &
                                                                                  // pswd.
        // extract meta data from the request
        String name = request.get("name");
        String email = request.get("email");
        String password = request.get("password");

        // check if the user already exists
        User existingUser = userService.getByEmail(email);
        if (existingUser != null) { // if the there exist a user with the same email then return a bad request
            return ResponseEntity.badRequest().body(Map.of("error", "Email already registered"));
        }

        // create a new user
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(password)); // this will hash the password
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());

        // save the user
        User savedUser = userService.create(user);

        // generate JWT token
        String token = jwtTokenProvider.generateToken(savedUser.getEmail());

        // return the response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", Map.of("id", savedUser.getId(),
                "name", savedUser.getName(),
                "email", savedUser.getEmail(),
                "role", savedUser.getRole()));

        return ResponseEntity.ok(response); // returns the Map containing token and user info
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        try {
            // authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));

            // if authentication is succesfull ( else we would have been into catch block)
            String token = jwtTokenProvider.generateToken(email);

            // get user details
            User user = userService.getByEmail(email);

            // return the response
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", Map.of(
                    "id", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "role", user.getRole()));

            return ResponseEntity.ok(response); // returns the Map containing token and user info
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        }

    }

}
