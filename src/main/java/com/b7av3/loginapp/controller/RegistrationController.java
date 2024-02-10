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

    /**
     * Displays the signup form with user type selection.
     *
     * @param type  the type of user being signed up (e.g., "user" or "admin")
     * @param model the Model object to populate data for the view
     * @return the view name for the signup form
     */
    @GetMapping("/signup")
    public String showSignUpForm(@RequestParam(value = "type", defaultValue = "user") String type, Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User()); // Ensure a new User is added
        }
        model.addAttribute("signupType", type);
        return "signup";
    }

    /**
     * Handles user registration.
     *
     * @param user   the User object containing registration data
     * @param result the BindingResult object for validation errors
     * @param model  the Model object to populate data for the view
     * @param type   the type of user being signed up (e.g., "user" or "admin")
     * @return the view name for the signup form or a redirect to the homepage
     */
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
