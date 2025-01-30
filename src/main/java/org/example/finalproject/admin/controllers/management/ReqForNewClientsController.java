package org.example.finalproject.admin.controllers.management;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.dtos.clients.ClientRequestDto;
import org.example.finalproject.user.entities.enums.*;
import org.example.finalproject.user.mappers.ClientMapper;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin-panel/management")
@RequiredArgsConstructor
public class ReqForNewClientsController {

    private final ClientRequestService service;
    private final ClientMapper mapper;

    @GetMapping("/requests")
    public String reqForNewClients(Model model) {
        var clientList = service.findAll();
        model.addAttribute("clientRequestList", clientList);
        model.addAttribute("statusEnum", StatusEnum.class);
        return "admin-view/management/requests/requests-list";
    }

    @GetMapping("/requests/{id}/save-client")
    public String saveClient(@PathVariable long id, Model model) {
        model.addAttribute("clientRequest", service.findById(id));
        model.addAttribute("clientRegistrationDto", new ClientRegistrationDto());
        model.addAttribute("preferredLanguages", PreferredLanguages.values());
        model.addAttribute("cities", Cities.values());
        model.addAttribute("packagesList", PackageEnum.values());
        model.addAttribute("deviceTypes", DevicesTypes.values());
        model.addAttribute("contractStatus", ContractStatus.values());

        ClientRequestDto clientDto = service.findById(id);
        String formattedRequestTime = clientDto.getRequestTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("formattedRequestTime", formattedRequestTime);

        return "admin-view/management/requests/save-client";
    }

    @PostMapping("/requests/{id}/save-client")
    public String saveClient(@Valid @ModelAttribute ClientRegistrationDto clientRegistrationDto,
                             BindingResult br,
                             @PathVariable long id,
                             RedirectAttributes redirectAttributes,
                             @SessionAttribute("admin") AdminEntity adminSession) {

        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "admin-view/management/requests/save-client";
        }

        fillDataAutomatically(clientRegistrationDto, adminSession);

        service.saveClient(clientRegistrationDto);
        redirectAttributes.addFlashAttribute("savedSuccessfully", "Client saved successfully!");

        return "redirect:/admin-panel/management/clients";
    }


    private void fillDataAutomatically(ClientRegistrationDto clientRegistrationDto, @SessionAttribute("admin") AdminEntity adminSession) {

        clientRegistrationDto.setStatus(StatusEnum.SAVED);
        clientRegistrationDto.setRegisteredBy(adminSession.getName() + " " + adminSession.getSurname());

        clientRegistrationDto.setContractStartDate(LocalDateTime.now());
        clientRegistrationDto.setContractExpiryDate(LocalDateTime.now().plusYears(1));
        clientRegistrationDto.setContractStatus(ContractStatus.ACTIVE);

        clientRegistrationDto.setSubscriptionStartDate(LocalDateTime.now());
        clientRegistrationDto.setSubscriptionEndDate(LocalDateTime.now().plusMonths(1));
        clientRegistrationDto.setClientNr((long) (Math.random() * 1000000));

        clientRegistrationDto.setSubscriptionStatus(ContractStatus.ACTIVE);

        clientRegistrationDto.setContractStatus(ContractStatus.ACTIVE);

    }

    @GetMapping("/requests/{id}/decline")
    public String declineRequest(@PathVariable long id, Model model) {
        model.addAttribute("clientRequest", service.findById(id));
        return "admin-view/management/requests/declined";
    }

    @PostMapping("/requests/{id}/decline")
    public String declineRequest(@PathVariable long id,
                                 RedirectAttributes redirectAttributes,
                                 @SessionAttribute("admin") AdminEntity adminSession,
                                 HttpSession session) {

        ClientRequestDto clientRequestDto = service.findById(id);

        if (clientRequestDto == null) {
            throw new EntityNotFoundException("Client request with id " + id + " not found");
        }

        clientRequestDto.setStatus(StatusEnum.DECLINED);
        clientRequestDto.setDeclinedBy(adminSession.getName() + " " + adminSession.getSurname());

        mapper.toEntity(clientRequestDto);
        service.add(clientRequestDto);

        redirectAttributes.addFlashAttribute("declinedMessage", "Request declined successfully!");


        return "redirect:/admin-panel/management/requests";
    }

    @PostMapping("/requests/{id}/reactivate")
    public String reactivateRequest(@PathVariable long id, RedirectAttributes redirectAttributes, @SessionAttribute("admin") AdminEntity adminSession) {
        ClientRequestDto clientRequestDto = service.findById(id);

        clientRequestDto.setStatus(StatusEnum.OPEN);
        clientRequestDto.setReactivatedBy(adminSession.getName() + " " + adminSession.getSurname());
        clientRequestDto.setReactivatedTime(LocalDateTime.now());

        service.add(clientRequestDto);
        redirectAttributes.addFlashAttribute("reactivatedMessage", "Request reactivated successfully!");
        return "redirect:/admin-panel/management/requests";
    }


}
