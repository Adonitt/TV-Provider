package org.example.finalproject.admin.controllers.management;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.entities.ClientsEntity;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.DevicesTypes;
import org.example.finalproject.user.entities.enums.PreferredLanguages;
import org.example.finalproject.user.entities.enums.StatusEnum;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin-panel/management")
@RequiredArgsConstructor
public class ReqForNewClientsController {

    private final ClientRequestService service;
    private final ClientsRepository repository;

    @GetMapping("/req-for-new-clients")
    public String reqForNewClients(Model model) {
        model.addAttribute("clientRequestList", service.findAll());
        return "admin-view/management/requests-for-new-clients/req-for-new-clients-list";
    }

    @GetMapping("/req-for-new-clients/{id}/save-client")
    public String saveClient(@PathVariable long id, Model model) {

        model.addAttribute("clientRequest", service.findById(id));
        model.addAttribute("clientRegistrationDto", new ClientRegistrationDto());
        model.addAttribute("preferredLanguages", PreferredLanguages.values());
        model.addAttribute("cities", Cities.values());
        model.addAttribute("packagesList", PackageEnum.values());
        model.addAttribute("deviceTypes", DevicesTypes.values());
        return "admin-view/management/requests-for-new-clients/save-client";
    }

    @PostMapping("/req-for-new-clients/{id}/save-client")
    public String saveClient(@Valid ClientRegistrationDto clientRegistrationDto,
                             BindingResult br,
                             @PathVariable long id,
                             RedirectAttributes redirectAttributes) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "admin-view/management/requests-for-new-clients/save-client";
        }

        clientRegistrationDto.setStatus(StatusEnum.SAVED);
        service.saveClient(clientRegistrationDto);
        redirectAttributes.addFlashAttribute("savedSuccessfully", "Client saved successfully!");
        return "redirect:/admin-panel/management/clients";
    }
}
