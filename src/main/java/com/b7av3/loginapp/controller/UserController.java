package com.b7av3.loginapp.controller;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/dashboard")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showDashboard(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName(); // Get the username of the logged-in user
            // Fetch user data from the database based on the username
            User user = userService.findByUsername(username);

            if (user != null) {
                model.addAttribute("user", user);
                return "dashboard";
            }
        }

        // If the user is not authenticated, they will still be able to access the dashboard page as an anonymous user
        return "dashboard";
    }
    // TODO: maybe i will add methods for user-related actions later.

}
