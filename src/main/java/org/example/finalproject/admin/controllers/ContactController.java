package org.example.finalproject.controllers.admin_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/contact-us-details")
    public String contact() {
        return "/admin-view/contact/contact";
    }

}
