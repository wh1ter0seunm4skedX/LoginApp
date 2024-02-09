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

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignUpForm(@RequestParam(value = "type", defaultValue = "user") String type, Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("signupType", type);
        return "signup";
    }

    @PostMapping("/signup")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
                           @RequestParam(value = "type", defaultValue = "user") String type) {
        if (result.hasErrors()) {
            model.addAttribute("signupType", type);
            return "signup";
        }

        if (!userService.registerNewUser(user, type)) {
            model.addAttribute("registrationError", "Username already exists.");
            model.addAttribute("signupType", type);
            return "signup";
        }

        return "redirect:/login";
    }
}
