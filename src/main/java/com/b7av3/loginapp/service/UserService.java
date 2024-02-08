package com.b7av3.loginapp.service;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Ensure this is uncommented and properly injected

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // Initialize the passwordEncoder
    }

    // Saves a user to the repository after encoding their password.
    public User saveUser(User user) {
        // Encode the password
     //   user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user
        return userRepository.save(user);
    }
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    // Finds a user by their username.
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    public boolean registerNewUser(User user) {
        // Check if user already exists
        if (userRepository.findByUsername(user.getUsername()) != null) {
            // User already exists
            return false;
        }
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the new user
        userRepository.save(user);
        return true;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // Here you can assign roles to the user and return them as authorities
        // For simplicity, let's grant every user a ROLE_USER authority.
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
