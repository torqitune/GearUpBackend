package com.example.demo.security;

import com.example.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * UserPrincipal wraps the User entity and implements Spring Security's
 * UserDetails interface.
 * This class represents the authenticated user in the security context.
 */
public class UserPrincipal implements UserDetails {

    // these are the fields that we want Spring to know about the user.
    private final Long id;
    private final String email;
    private final String passwordHash;
    private final String role;
    private final String name;


    // constructor for UserPrincipal
    public UserPrincipal(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.passwordHash = user.getPasswordHash();
        this.role = user.getRole();
        this.name = user.getName();
    }

    // Getter for user ID (useful in controllers)
    public Long getId() {
        return id;
    }

    // Getter for user name (useful in controllers)
    public String getName() {
        return name;
    }

    // Getter for email (useful in controllers)
    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // this method returns the roles of the user
        // Convert the role string to a GrantedAuthority
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));   // return the role as a GrantedAuthority
    }

    @Override
    public String getPassword() { // this method returns the password of the user
        return passwordHash;
    }

    @Override
    public String getUsername() { // this method returns the username of the user
        // Using email as the username
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
