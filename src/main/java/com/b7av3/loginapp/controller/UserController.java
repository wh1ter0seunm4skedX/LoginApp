package com.b7av3.loginapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/dashboard")
    public String showDashboard(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        // Add the username to the model to display it in the dashboard view
        model.addAttribute("username", currentUser.getUsername());

        // Return the name of the Thymeleaf template for the dashboard page
        return "dashboard";
    }

    // TODO: maybe i will add methods for user-related actions later.
}
