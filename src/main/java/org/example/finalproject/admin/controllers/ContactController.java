package org.example.finalproject.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-af")

public class ContactController {

    @GetMapping("/contact-us-details")
    public String contact() {
        return "admin-view/contact/contact";
    }

}
