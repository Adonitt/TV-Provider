package org.example.finalproject.user.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.services.interfaces.ChannelService;
import org.example.finalproject.admin.services.interfaces.PackageService;
import org.example.finalproject.admin.services.interfaces.WebsiteService;
import org.example.finalproject.user.services.impls.PageViewCounterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController2 {
    private final PackageService packageService;
    private final ChannelService channelService;
    private final WebsiteService websiteService;
    private final PageViewCounterService pageViewCounterService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("website", websiteService.fetchData());
        model.addAttribute("indexCounter", pageViewCounterService.incrementCounter("index"));
        return "user-view/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("aboutCounter", pageViewCounterService.incrementCounter("about"));
        return "user-view/about";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("packages", packageService.findAll());
        model.addAttribute("servicesCounter", pageViewCounterService.incrementCounter("services"));
        return "user-view/services";
    }

    @GetMapping("/tv-channels")
    public String tvChannels(Model model) {
        model.addAttribute("channels", channelService.findAll());
        model.addAttribute("tvChannelsCounter", pageViewCounterService.incrementCounter("tv-channels"));
        return "user-view/tv-channels";
    }

    @GetMapping("/pricing")
    public String pricing(Model model) {
        model.addAttribute("packages", packageService.findAll());
        model.addAttribute("pricingCounter", pageViewCounterService.incrementCounter("pricing"));
        return "user-view/pricing";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactCounter", pageViewCounterService.incrementCounter("contact"));
        return "user-view/contact";
    }
}
