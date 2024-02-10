package com.b7av3.loginapp.model;

import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.Set;


// This class represents a User entity in the application.
@Entity
@Table(name = "users") // had to pick different table name that is not a reserved keyword in SQL
public class User {

    // The primary key for the User entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User's username, must be between 4 and 15 characters in length
    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 15)
    private String username;

    // User's password, must be at least 4 characters in length
    @Column(nullable = false)
    @Size(min = 4, max=256)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    // Default constructor for User
    public User() {}

    // Constructor for User with username and password parameters
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter method for retrieving the User's ID
    public Long getId() {
        return id;
    }

    // Setter method for setting the User's ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter method for retrieving the User's username
    public String getUsername() {
        return username;
    }

    // Setter method for setting the User's username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for retrieving the User's password
    public String getPassword() {
        return password;
    }

    // Setter method for setting the User's password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter method for retrieving the User's roles
    public Set<String> getRoles() {
        return roles;
    }
    // Setter method for setting the User's roles
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
