package org.example.finalproject.controllers.admin_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PackagesController {

    @GetMapping("/tv-packages")
    public String packages(){
        return "/admin-view/packages/tv-packages";
    }

    @GetMapping("/phone-packages")
    public String phonePackages(){
        return "/admin-view/packages/phone-packages";
    }
}
