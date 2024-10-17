package com.tienphuckx.learnspringsec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccessController {

    @GetMapping("/pub")
    public String publicPage() {
        return "public-page";  // Corresponds to public-page.html
    }

    @GetMapping("/user/home")
    public String userPage() {
        return "welcome-user";  // Corresponds to welcome-user.html
    }

    @GetMapping("/admin/home")
    public String adminPage() {
        return "welcome-admin";  // Corresponds to welcome-admin.html
    }

    @GetMapping("/login")
    public String login() {
        return "custom_login";
    }

//    @PostMapping("/perform_login")
//    public String performLogin() {
//
//    }
}
