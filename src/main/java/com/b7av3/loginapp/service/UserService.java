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
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service class for managing users, implements UserDetailsService for Spring Security integration.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection for UserRepository and PasswordEncoder
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register a new user with provided user details and type.
     * @param user The user to register.
     * @param type The type of user (e.g., "admin" or "user").
     * @return True if the user was successfully registered, false if the username already exists.
     */
    public boolean registerNewUser(User user, String type) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false; // User already exists
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode the password

        // Determine roles based on the type
        Set<String> roles = new HashSet<>();
        if ("admin".equals(type)) {
            roles.add("ROLE_ADMIN");
        } else {
            roles.add("ROLE_USER");
        }
        user.setRoles(roles); // Set roles for the user

        userRepository.save(user); // Save the user
        return true;
    }

    /**
     * Find a user by username.
     * @param username The username of the user to find.
     * @return The User object if found, otherwise null.
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Load user details by username for authentication.
     * @param username The username of the user to load.
     * @return UserDetails object representing the user.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    /**
     * Delete all users (Transactional operation).
     */
    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    /**
     * Get authorities (roles) for a user.
     * @param user The user to get authorities for.
     * @return Collection of GrantedAuthority objects.
     */
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
