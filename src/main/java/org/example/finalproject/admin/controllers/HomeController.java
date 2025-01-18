package org.example.finalproject.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel")
public class HomeController {

    @GetMapping("/dashboard")
    public String home() {
        return"admin-view/dashboard";
    }


}
