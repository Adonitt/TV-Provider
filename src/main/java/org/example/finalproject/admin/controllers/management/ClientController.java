package org.example.finalproject.admin.controllers.management;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.example.finalproject.admin.models.admin.AdminRole;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.admin.services.interfaces.PackageService;
import org.example.finalproject.user.dtos.clients.ClientDto;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.dtos.clients.ClientRequestDto;
import org.example.finalproject.user.entities.enums.*;
import org.example.finalproject.user.mappers.ClientMapper;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
@RequestMapping("/admin-panel/management/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientsRepository clientsRepository;
    private final ClientMapper mapper;
    private final ClientRequestService service;
    private final PackageService packageService;

    @GetMapping("")
    public String clients(Model model) {
        model.addAttribute("clientsList", clientsRepository.findAll());
        model.addAttribute("roles", AdminRole.class);
        return "admin-view/management/clients/clients-list";
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

        if (clientDto.getModifiedTime() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            model.addAttribute("formattedModifiedTime", clientDto.getModifiedTime().format(formatter));
        }
        model.addAttribute("formattedRequestTime", formattedRequestTime);
        model.addAttribute("formattedRegisteredTime", formattedRegisteredTime);
        model.addAttribute("formattedContractDate", formattedContractDate);
        model.addAttribute("formattedExpiryDate", formattedExpiryDate);
        model.addAttribute("formattedSubscriptionStartDate", formattedSubscriptionStartDate);
        model.addAttribute("formattedSubscriptionEndDate", formattedSubscriptionEndDate);

        model.addAttribute("clientDto", clientDto);
        return "admin-view/management/clients/details";
    }


    @GetMapping("/{id}/edit")
    public String editClient(@PathVariable long id, Model model) {
        model.addAttribute("clientDto", service.findClientById(id));
        model.addAttribute("preferredLanguages", PreferredLanguages.values());
        model.addAttribute("packagesList", PackageEnum.values());
        model.addAttribute("deviceTypes", DevicesTypes.values());
        model.addAttribute("packageList", packageService.findAll());


        return "admin-view/management/clients/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateClient(@PathVariable long id, @Valid @ModelAttribute ClientDto clientDto,
                               BindingResult br,
                               RedirectAttributes ra, @SessionAttribute("admin") AdminEntity adminSession, Map map) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "admin-view/management/clients/edit";
        }

        clientDto.setId(id);
        clientDto.setModifiedBy(adminSession.getName() + " " + adminSession.getSurname());

        clientDto.setModifiedTime(LocalDateTime.now());

        mapper.toClientEntity(clientDto);
        service.modify(clientDto, id);
        ra.addFlashAttribute("editedMessage", "Client with id " + id + " edited successfully!");
        return "redirect:/admin-panel/management/clients";
    }

    @GetMapping("/create")
    public String createClient(Model model) {
        model.addAttribute("clientDto", new ClientDto());
        model.addAttribute("preferredLanguages", PreferredLanguages.values());
        model.addAttribute("packagesList", PackageEnum.values());
        model.addAttribute("deviceTypes", DevicesTypes.values());
        model.addAttribute("citiesList", Cities.values());
        model.addAttribute("packageList", packageService.findAll());
        return "admin-view/management/clients/create";
    }

    @PostMapping("/create")
    public String createClient(@Valid @ModelAttribute ClientDto clientDto,
                               BindingResult br,
                               RedirectAttributes ra,
                               @SessionAttribute("admin") AdminEntity adminSession,
                               Model model) {

        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "admin-view/management/clients/create";
        }
        if (clientsRepository.existsByEmail(clientDto.getEmail())) {
            model.addAttribute("emailExists", "Request with this email already exists!");
            model.addAttribute("clientDto", clientDto);
            model.addAttribute("preferredLanguages", PreferredLanguages.values());
            model.addAttribute("packagesList", PackageEnum.values());
            model.addAttribute("deviceTypes", DevicesTypes.values());
            model.addAttribute("citiesList", Cities.values());
            model.addAttribute("packageList", packageService.findAll());
            return "admin-view/management/clients/create";
        }


        mapper.toClientEntity(clientDto);
        clientDto.setRegisteredBy(adminSession.getName() + " " + adminSession.getSurname());
        var selectedPlan = packageService.findById(clientDto.getSubscriptionPlan().getId());
        clientDto.setSubscriptionPlan(selectedPlan);

        fillDataAutomatically(clientDto);
        service.createClientManually(clientDto);
        ra.addFlashAttribute("savedSuccessfully", "Client saved successfully!");
        return "redirect:/admin-panel/management/clients";
    }

    private void fillDataAutomatically(ClientDto clientDto) {

        clientDto.setStatus(StatusEnum.SAVED);

        clientDto.setContractDate(LocalDateTime.now());
        clientDto.setContractExpiryDate(LocalDateTime.now().plusYears(1));


        clientDto.setSubscriptionStartDate(LocalDateTime.now());
        clientDto.setSubscriptionEndDate(LocalDateTime.now().plusMonths(1));
        clientDto.setClientNr((long) (Math.random() * 1000000));

        clientDto.setContractStatus(ContractStatus.ACTIVE);
        clientDto.setSubscriptionStatus(ContractStatus.ACTIVE);
    }

    @PostMapping("/{id}/extend-subscription")
    public String extendSubscription(@PathVariable Long id,
                                     @SessionAttribute("admin") AdminEntity admin,
                                     RedirectAttributes redirectAttributes) {
        boolean success = service.extendSubscriptionByOneMonth(id, admin.getName());

        if (success) {
            redirectAttributes.addFlashAttribute("successSub", "Subscription extended by one month for client with id " + id + "!");
        } else {
            redirectAttributes.addFlashAttribute("errorSub", "Client with id " + id + " not found or subscription not active.");
        }

        return "redirect:/admin-panel/management/clients";
    }

    @PostMapping("/{id}/extend-contract")
    public String extendContract(@PathVariable Long id,
                                 @SessionAttribute("admin") AdminEntity admin,
                                 RedirectAttributes redirectAttributes) {
        boolean success = service.extendSubscriptionByOneYear(id, admin.getName());

        if (success) {
            redirectAttributes.addFlashAttribute("successContract", "Contract extended by one year for client with id " + id + "!");
        } else {
            redirectAttributes.addFlashAttribute("errorContract", "Client with id " + id + " not found or subscription not active.");
        }

        return "redirect:/admin-panel/management/clients";
    }

}
