package com.b7av3.loginapp.service;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Saves a user to the repository after encoding their password.
    public User saveUser(User user) {
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
