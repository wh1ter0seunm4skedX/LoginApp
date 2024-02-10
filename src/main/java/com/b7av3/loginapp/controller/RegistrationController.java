package com.b7av3.loginapp.controller;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    // Constructor injection for UserService
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // Show the signup form with user type selection
    @GetMapping("/signup")
    public String showSignUpForm(@RequestParam(value = "type", defaultValue = "user") String type, Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User()); // Ensure a new User is added
        }
        model.addAttribute("signupType", type);
        return "signup";
    }

    // Handle user registration
    @PostMapping("/signup")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
                           @RequestParam(value = "type", defaultValue = "user") String type) {
        // Validate user input
        if (result.hasErrors()) {
            model.addAttribute("signupType", type);
            return "signup";
        }

        // Attempt to register the new user
        if (!userService.registerNewUser(user, type)) {
            model.addAttribute("registrationError", "Username already exists.");
            model.addAttribute("signupType", type);
            return "signup";
        }

        // Redirect to homepage with signup success indicator
        return "redirect:/?signupSuccess=true";
    }
}
