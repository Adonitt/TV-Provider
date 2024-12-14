package org.example.finalproject.user.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController2 {

    @GetMapping("/")
    public String index() {
        return "user-view/index";
    }

    @GetMapping("/about")
    public String about() {
        return "user-view/about";
    }

    @GetMapping("/services")
    public String services() {
        return "user-view/services";
    }

    @GetMapping("/tv-channels")
    public String tvChannels() {
        return "user-view/tv-channels";
    }

    @GetMapping("/pricing")
    public String pricing() {
        return "user-view/pricing";
    }

    @GetMapping("/contact")
    public String contact() {
        return "user-view/contact";
    }


}
