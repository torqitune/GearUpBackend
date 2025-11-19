package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to users
    @ManyToOne
    @JoinColumn(
            name="user_id",     // name depicts the column name in the images table
            foreignKey=@ForeignKey(name="images_user_id_fkey"), // foreign key constraint name
            referencedColumnName = "id"     // the column in the users table that this foreign key references
    )
    private User user;

    // Foreign key to events
    @ManyToOne
    @JoinColumn(
            name="event_id",    // name depicts the column name in the images table
            foreignKey=@ForeignKey(name="images_event_id_fkey"),        // giving a name to the foreign key constraint
            referencedColumnName = "id")        // the column in the events table that this foreign key references
    private Event event;

    @Column(name="file_url", nullable=false, length=255)
    private String fileUrl;

    @Column(length=20)
    private String status = "PENDING";

    @Column(name="created_at")
    private LocalDateTime createdAt;

    // getters and setters
    public void setStatus(String status) {
        this.status = status;
    }
}