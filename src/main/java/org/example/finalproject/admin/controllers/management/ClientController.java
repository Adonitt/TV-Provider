package org.example.finalproject.admin.controllers.management;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.dtos.clients.ClientDto;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.PreferredLanguages;
import org.example.finalproject.user.mappers.ClientMapper;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin-panel/management/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientsRepository clientsRepository;
    private final ClientMapper mapper;

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
    public String details(@PathVariable Long id, Model model) {
        var clientEntity = clientsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found"));
        var clientDto = mapper.toClientDto(clientEntity);

        String formattedRequestTime = clientDto.getRequestTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formattedRegisteredTime = clientDto.getRegisteredTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formattedContractDate = clientDto.getContractDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formattedExpiryDate = clientDto.getContractExpiryDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formattedSubscriptionStartDate = clientDto.getSubscriptionStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formattedSubscriptionEndDate = clientDto.getSubscriptionEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        // Add formatted dates to model
        model.addAttribute("formattedRequestTime", formattedRequestTime);
        model.addAttribute("formattedRegisteredTime", formattedRegisteredTime);
        model.addAttribute("formattedContractDate", formattedContractDate);
        model.addAttribute("formattedExpiryDate", formattedExpiryDate);
        model.addAttribute("formattedSubscriptionStartDate", formattedSubscriptionStartDate);
        model.addAttribute("formattedSubscriptionEndDate", formattedSubscriptionEndDate);

        model.addAttribute("clientDto", clientDto);
        return "admin-view/management/clients/details";
    }

    private void formatRequestTime(ClientDto clientDto, Model model) {
    }

}
