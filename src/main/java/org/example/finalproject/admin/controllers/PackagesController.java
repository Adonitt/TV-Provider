package org.example.finalproject.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-af")

public class PackagesController {


    @GetMapping("/packages")
    public String packages() {
        return "admin-view/packages/tv-packages/tv-packages";
    }

    @GetMapping("/phone-packages")
    public String phonePackages() {
        return "admin-view/packages/phone-packages/phone-packages";
    }
}
