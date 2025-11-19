package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity     // marking this class as a entity i.e entity mapped to a database table
@Data       // data annotation to generate getters and setters
@Table(name = "users")      // specify table name in the database
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=100)
    private String name;

    @Column(nullable=false, unique=true, length=150)
    private String email;

    @Column(name="password_hash", nullable=false, length=255)
    private String passwordHash;

    @Column(name="role", nullable=false, length=20)
    private String role = "USER";   // default

    @Column(name="created_at")
    private LocalDateTime createdAt;
}