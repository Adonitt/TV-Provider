package org.example.finalproject.admin.controllers.management;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.dtos.clients.ClientRequestDto;
import org.example.finalproject.user.entities.ClientRequestEntity;
import org.example.finalproject.user.entities.ClientsEntity;
import org.example.finalproject.user.entities.enums.*;
import org.example.finalproject.user.mappers.ClientMapper;
import org.example.finalproject.user.repositories.ClientRequestRepository;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin-panel/management")
@RequiredArgsConstructor
public class ReqForNewClientsController {

    private final ClientRequestService service;
    private final ClientMapper mapper;

    @GetMapping("/requests")
    public String reqForNewClients(Model model) {
        var clientList = service.findAll(); // Fetch mapped data
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
        model.addAttribute("contractType", ContractType.values());
        model.addAttribute("contractStatus", ContractStatus.values());

        ClientRequestDto clientDto = service.findById(id);
        String formattedRequestTime = clientDto.getRequestTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("formattedRequestTime", formattedRequestTime);

        return "admin-view/management/requests/save-client";
    }

    @PostMapping("/requests/{id}/save-client")
    public String saveClient(ClientRegistrationDto clientRegistrationDto,
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
        clientRegistrationDto.setSubscriptionActive(true);
        clientRegistrationDto.setRegisteredBy(adminSession.getName() + " " + adminSession.getSurname());

        clientRegistrationDto.setContractDate(LocalDateTime.now());

        if (clientRegistrationDto.getContractType() == ContractType.MONTHLY) {
            clientRegistrationDto.setExpiryDate(LocalDateTime.now().plusMonths(1));
        } else if (clientRegistrationDto.getContractType() == ContractType.YEARLY) {
            clientRegistrationDto.setExpiryDate(LocalDateTime.now().plusYears(1));
        }

        if (clientRegistrationDto.getExpiryDate() != null && clientRegistrationDto.getExpiryDate().isBefore(LocalDateTime.now())) {
            clientRegistrationDto.setContractStatus(ContractStatus.INACTIVE);
        } else {
            clientRegistrationDto.setContractStatus(ContractStatus.ACTIVE);
        }

    }

    @GetMapping("/requests/{id}/decline")
    public String declineRequest(@PathVariable long id, Model model) {
        model.addAttribute("clientRequest", service.findById(id));

        return "admin-view/management/requests/declined";
    }

    @PostMapping("/requests/{id}/decline")
    public String declineRequest(@PathVariable long id, RedirectAttributes redirectAttributes,
                                 HttpSession session,
                                 @SessionAttribute("admin") AdminEntity adminSession) {
        ClientRequestDto clientRequestDto = service.findById(id);

        if (clientRequestDto == null) {
            throw new EntityNotFoundException("Client request with id " + id + " not found");
        }

        clientRequestDto.setStatus(StatusEnum.DECLINED);
        clientRequestDto.setDeclinedBy(adminSession.getName() + " " + adminSession.getSurname());

        session.setAttribute("status", StatusEnum.DECLINED);

        mapper.toEntity(clientRequestDto);

        service.add(clientRequestDto);

        redirectAttributes.addFlashAttribute("declinedMessage", "Request declined successfully!");
        return "redirect:/admin-panel/management/requests";
    }
}
