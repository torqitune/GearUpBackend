package com.example.demo.repository;        // package declaration

import com.example.demo.entity.User;        // import User entity
import org.springframework.data.jpa.repository.JpaRepository;   // import JpaRepository from Spring Data JPA


//declaring an interface UserRepository that extends JpaRepository to provide CRUD operations for User entity , an interface is used
public interface UserRepository extends JpaRepository<User, Long> {     //this interface will inherit methods for CRUD operations from JpaRepository , also by extending JPARepository we get methods like save, findAll, findById, deleteById etc for free
    User findByEmail(String email); // custom query
}