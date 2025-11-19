package com.example.demo.mapper;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;

public class UserMapper {
    // this method converts User entity to UserResponse DTO
    public static User toEntity(CreateUserRequest createUserRequest) {
        User user = new User();     // creating an empty user
        user.setName(createUserRequest.getName());         // setting name
        user.setEmail(createUserRequest.getEmail());
        user.setPasswordHash(createUserRequest.getPassword());
        user.setRole("USER");
        return user;    // returning the user
    }

    // this method converts User entity to UserResponse DTO
    public static UserResponse toResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setCreatedAt(user.getCreatedAt());
        return res;
    }
}
