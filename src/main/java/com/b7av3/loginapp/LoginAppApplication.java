package com.b7av3.loginapp;

import com.b7av3.loginapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LoginAppApplication {

    // Main method to start the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(LoginAppApplication.class, args);
    }
    /*
    // method for running and debugging - testing purposes only -
    // use this if you want to delete the user database.
    @Bean
    public CommandLineRunner setupDefaultUser(UserService userService, PasswordEncoder passwordEncoder) {
        return args -> {
            // Clear the existing users from the database
            userService.deleteAllUsers();
        };
    }
     */

}
