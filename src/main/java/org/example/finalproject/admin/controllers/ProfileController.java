package org.example.finalproject.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-af")

public class ProfileController {


    @GetMapping("/profile")
    public String profile() {
        return "admin-view/profile/profile";
    }

    @GetMapping("/profile/change-password")
    public String changePassword() {
        return "admin-view/profile/change-password";
    }

}
