package org.example.finalproject.admin.controllers.management;

import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.dtos.clientRegistration.ClientRegistrationDto;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.PreferredLanguages;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel/management/clients")
public class ClientController {

    @GetMapping("")
    public String clients() {
        return "admin-view/management/clients/clients-list";
    }

    @GetMapping("/new")
    public String newClient(Model model) {
        model.addAttribute("clientRegistrationDto", new ClientRegistrationDto());
        model.addAttribute("preferredLanguages", PreferredLanguages.values());
        model.addAttribute("cities", Cities.values());
        model.addAttribute("packagesList", PackageEnum.values());
        return "admin-view/management/clients/new-client";
    }

    @GetMapping("{id}/details")
    public String details() {

        return "admin-view/management/clients/details";
    }
}
