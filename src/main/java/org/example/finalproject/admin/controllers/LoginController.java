package org.example.finalproject.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-pw")

public class LoginController {


    @GetMapping("/")
    public String login() {
        return "admin-view/login-admin/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {

        return "admin-view/login-admin/forgot-password";
    }

    @GetMapping("/**")
    public String error() {
        return "admin-view/login-admin/login";
    }
}
