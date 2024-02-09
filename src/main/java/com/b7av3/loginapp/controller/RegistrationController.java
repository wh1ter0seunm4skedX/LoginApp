package com.b7av3.loginapp.controller;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        // First, check if the username already exists to avoid unnecessary validation
        if (!result.hasErrors() && !userService.registerNewUser(user)) {
            model.addAttribute("registrationError", "Username already exists.");
            return "signup";
        }

        // Handle validation errors for username and password individually
        boolean usernameError = false;
        boolean passwordError = false;
        for (FieldError fieldError : result.getFieldErrors()) {
            if ("username".equals(fieldError.getField())) {
                model.addAttribute("usernameError", fieldError.getDefaultMessage());
                usernameError = true;
            } else if ("password".equals(fieldError.getField())) {
                model.addAttribute("passwordError", fieldError.getDefaultMessage());
                passwordError = true;
            }
        }

        // If there were any errors related to username or password, return to the signup page
        if (usernameError || passwordError || result.hasErrors()) {
            return "signup";
        }

        // If no errors, redirect to the login page after successful registration
        return "redirect:/login";
    }
}
