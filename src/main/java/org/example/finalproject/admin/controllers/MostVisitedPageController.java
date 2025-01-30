package org.example.finalproject.admin.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.user.services.impls.PageViewCounterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel/most-visited")
@RequiredArgsConstructor
public class MostVisitedPageController {

    private final PageViewCounterService pageViewCounterService;

    @GetMapping("")
    public String mostVisitedPage(Model model) {
        model.addAttribute("indexCounter", pageViewCounterService.getCounter("index"));
        model.addAttribute("aboutCounter", pageViewCounterService.getCounter("about"));
        model.addAttribute("servicesCounter", pageViewCounterService.getCounter("services"));
        model.addAttribute("tvChannelsCounter", pageViewCounterService.getCounter("tv-channels"));
        model.addAttribute("pricingCounter", pageViewCounterService.getCounter("pricing"));
        model.addAttribute("contactCounter", pageViewCounterService.getCounter("contact"));
        model.addAttribute("getStartedCounter", pageViewCounterService.getCounter("get-started"));
        model.addAttribute("ePaymentCounter", pageViewCounterService.getCounter("e-payment"));
        return "admin-view/web-page-details/most-visited/most-visited";
    }
}


