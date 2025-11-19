// Repository interface for Event entity



package com.example.demo.repository;

import com.example.demo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;


// repository in spring boot is like a DB manager , acts as a bridge between the application and the database , it gives methods to perform CRUD operations
// here in the parameter we specify the entity type and the type of its primary key
public interface EventRepository extends JpaRepository<Event, Long> {}      // this is the repository interface for Event entity , extending JpaRepository to provide CRUD operations