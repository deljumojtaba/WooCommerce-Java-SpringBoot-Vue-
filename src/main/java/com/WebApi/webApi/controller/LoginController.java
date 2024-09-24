package com.WebApi.webApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "Welcome to the Login Page!";
    }

    @GetMapping("/register")
    public String register() {
        return "This is the register page!";
    }

}
