package com.b7av3.loginapp.repository;

import com.b7av3.loginapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// This interface defines a UserRepository for managing User entities.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query method to find a user by username
    User findByUsername(String username);
}
