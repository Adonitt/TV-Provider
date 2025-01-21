package org.example.finalproject.admin.controllers.management;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.PreferredLanguages;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel/management/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientsRepository clientsRepository;

    @GetMapping("")
    public String clients(Model model) {
        model.addAttribute("clientsList", clientsRepository.findAll());
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
