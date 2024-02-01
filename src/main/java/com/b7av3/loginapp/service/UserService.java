package com.b7av3.loginapp.service;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// This class provides services related to User entities.
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    // Registers a new user by encoding their password and saving them in the repository.
    public User registerNewUser(User user) {
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user
        return userRepository.save(user);
    }

    // Finds a user by their username.
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
