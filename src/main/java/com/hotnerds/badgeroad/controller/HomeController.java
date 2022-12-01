package com.hotnerds.badgeroad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "hello";
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("login")
    public String login() {return "login";}
}