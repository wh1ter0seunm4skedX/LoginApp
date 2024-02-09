package com.b7av3.loginapp.controller;

import com.b7av3.loginapp.model.User;
import com.b7av3.loginapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "type", required = false) String type, Model model) {
        System.out.println("Login type received: " + type); // Simple logging for debugging
        model.addAttribute("user", new User());
        model.addAttribute("loginType", type != null ? type : "user"); // Default to user
        return "login";
    }

}
