package org.example.finalproject.admin.controllers.management;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.entities.ClientRequestEntity;
import org.example.finalproject.user.entities.ClientsEntity;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.DevicesTypes;
import org.example.finalproject.user.entities.enums.PreferredLanguages;
import org.example.finalproject.user.entities.enums.StatusEnum;
import org.example.finalproject.user.repositories.ClientRequestRepository;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin-panel/management")
@RequiredArgsConstructor
public class ReqForNewClientsController {

    private final ClientRequestService service;
    private final ClientRequestRepository clientRequestRepository;

    @GetMapping("/req-for-new-clients")
    public String reqForNewClients(Model model) {
        var clientList = service.findAll(); // Fetch mapped data
        model.addAttribute("clientRequestList", clientList);
        model.addAttribute("statusEnum", StatusEnum.class);
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
                             RedirectAttributes redirectAttributes,

                             @SessionAttribute("admin") AdminEntity adminSession) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "admin-view/management/requests-for-new-clients/save-client";
        }

        clientRegistrationDto.setStatus(StatusEnum.SAVED);
        clientRegistrationDto.setSubscriptionActive(true);
        clientRegistrationDto.setRegisteredBy(adminSession.getName() + " " + adminSession.getSurname());
        service.saveClient(clientRegistrationDto);
        redirectAttributes.addFlashAttribute("savedSuccessfully", "Client saved successfully!");
        return "redirect:/admin-panel/management/clients";
    }

    @GetMapping("/req-for-new-clients/{id}/decline")
    public String declineRequest(@PathVariable long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("declinedSuccessfully", "Request Declined!");
        return "redirect:/admin-panel/management/req-for-new-clients";
    }

    @PostMapping("/req-for-new-clients/{id}/decline")
    public String declineRequest(RedirectAttributes ra) {

        ra.addFlashAttribute("declinedMessage", "Request Declined Successfully!");
        return "redirect:/admin-panel/management/req-for-new-clients/{id}/decline";
    }
}
