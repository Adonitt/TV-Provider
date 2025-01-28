package org.example.finalproject.admin.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.services.interfaces.AdminService;
import org.example.finalproject.admin.services.interfaces.ChannelService;
import org.example.finalproject.admin.services.interfaces.PackageService;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel")
@RequiredArgsConstructor
public class HomeController {
    private final AdminService adminService;
    private final ClientRequestService clientRequestService;
    private final ClientsRepository clientsRepository;
    private final PackageService packageService;
    private final ChannelService channelService;

    @GetMapping("/dashboard")
    public String home(Model model) {
        model.addAttribute("admins", adminService.findAll());
        model.addAttribute("clientRequests", clientRequestService.findAll());
        model.addAttribute("clients", clientsRepository.findAll());
        model.addAttribute("packages", packageService.findAll());
        model.addAttribute("channels", channelService.findAll());
        return "admin-view/dashboard";
    }

    @GetMapping("/**")
    public String error() {
        return "admin-view/dashboard";
    }

}
