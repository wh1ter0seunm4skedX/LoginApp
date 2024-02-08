package com.b7av3.loginapp.controller;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
/*
    @GetMapping("signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("signup")
    public String registerUser(User user) {
        userService.registerNewUser(user); // Ensure this method handles password encoding
        return "redirect:/login?success";
    }

 */
}
