package org.example.finalproject.user.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.services.interfaces.ChannelService;
import org.example.finalproject.admin.services.interfaces.PackageService;
import org.example.finalproject.admin.services.interfaces.WebsiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController2 {
    private final PackageService packageService;
    private final ChannelService channelService;
    private final WebsiteService websiteService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("website", websiteService.fetchData());
        return "user-view/index";
    }

    @GetMapping("/about")
    public String about() {
        return "user-view/about";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("packages", packageService.findAll());
        return "user-view/services";
    }

    @GetMapping("/tv-channels")
    public String tvChannels(Model model) {
        model.addAttribute("channels", channelService.findAll());
        return "user-view/tv-channels";
    }


    @GetMapping("/pricing")
    public String pricing(Model model) {
        model.addAttribute("packages", packageService.findAll());
        return "user-view/pricing";
    }

    @GetMapping("/contact")
    public String contact() {
        return "user-view/contact";
    }

//    @GetMapping("/**")
//    public String error() {
//        return "user-view/error";
//    }

}
