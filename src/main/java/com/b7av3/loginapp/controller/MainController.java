package com.b7av3.loginapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /**
     * Displays the landing page.
     *
     * @return the view name for the landing page
     */
    @GetMapping("/")
    public String showLandingPage() {
        return "landing";
    }
}
