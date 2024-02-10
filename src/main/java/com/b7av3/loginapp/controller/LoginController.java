package com.b7av3.loginapp.controller;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Displays the login form.
     *
     * @param type  the type of login (e.g., "user" or "admin")
     * @param model the Model object to populate data for the view
     * @return the view name for the login page
     */
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "type", required = false) String type, Model model) {
        // Check if the user is already authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            // User is already logged in, redirect to dashboard
            return "redirect:/dashboard";
        }

        // Add an empty user object to the model
        model.addAttribute("user", new User());

        // Add the login type to the model, defaulting to "user" if not provided
        model.addAttribute("loginType", type != null ? type : "user");

        return "login"; // Return the login page
    }
}
