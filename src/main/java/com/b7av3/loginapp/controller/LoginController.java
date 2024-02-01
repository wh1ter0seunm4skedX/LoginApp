package com.b7av3.loginapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    // Return the name of the Thymeleaf template for the login page
    @GetMapping
    public String showLoginForm() {
        return "login";
    }
}
