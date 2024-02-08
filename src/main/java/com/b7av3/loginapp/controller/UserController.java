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
            String username = principal.getName();
            User user = userService.findByUsername(username);
            if (user != null) {
                model.addAttribute("user", user);
            } else {
                // Handle case where user is not found
                System.out.println("User not found: " + username); // Consider using a logger
                return "redirect:/login"; // Redirect to login or an error page
            }
        }
        return "dashboard";
    }

    // TODO: maybe i will add methods for user-related actions later.

}
