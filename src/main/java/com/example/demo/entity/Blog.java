package com.example.demo.entity;

import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "blogs")
public class Blog {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) // this will auto generate the primary key value
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id", // name here means the column inside this table (Blogs)
                        referencedColumnName = "id", // referencedColumnName here means the column inside the User table
                                                     // that
                        foreignKey = @ForeignKey(name = "blog_user_id_fkey") // foreigKey lets use a custom name the
                                                                             // foreign key constraint , without this
                                                                             // JPA will generate a name automatically
                                                                             // i.e something like this
                                                                             // FKj3h4k2j3h4k2j3h4k2j3h4k2
        )
        private User user; // this column will reference the users table

        @ManyToOne
        @JoinColumn(name = "event_id", // name here means the column inside this table (Blogs)
                        referencedColumnName = "id", // referencedColumnName here means the column inside the Event
                                                     // table that
                        foreignKey = @ForeignKey(name = "fk_blogs_events") // this lets use the custom name for the
                                                                           // foreign key constraint
        )
        private Event event; // this column will reference the events tabled

        @Column(name = "content", columnDefinition = "TEXT") // this column will store the blog content
        private String content;

        @Column(length = 20, nullable = false) // this column will store the status of the blog
        private String status = "PENDING"; // default value is PENDING

        // timestamp for when the blog was created
        @CreationTimestamp
        @Column(name = "created_at", updatable = false) // updatable = false ensures that the column is not updated
                                                        // after created (immutable)
        private LocalDateTime createdAt;

        @Column(name = "title", nullable = false, length = 255)
        private String title;

        // --- Getters and Setters ---
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        } // this method sets the value of the id field

        public User getUser() {
                return user;
        } // this method returns the value of the user field

        public void setUser(User user) {
                this.user = user;
        } // this method sets the value of the user field

        public Event getEvent() {
                return event;
        } // this method returns the value of the event field

        public void setEvent(Event event) {
                this.event = event;
        } // this method sets the value of the event field

        public String getContent() {
                return content;
        } // this method returns the value of the content field

        public void setContent(String content) {
                this.content = content;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
                this.createdAt = createdAt;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

}
