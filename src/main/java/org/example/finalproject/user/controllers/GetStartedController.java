package org.example.finalproject.user.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.services.interfaces.PackageService;
import org.example.finalproject.user.dtos.clients.ClientRequestDto;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.StatusEnum;
import org.example.finalproject.user.repositories.ClientRequestRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/get-started")
@RequiredArgsConstructor
public class GetStartedController {

    private final ClientRequestService service;
    private final PackageService packageService;
    private final ClientRequestRepository clientRequestRepository;

    @GetMapping("")
    public String getStarted(Model model) {
        model.addAttribute("clientRequestDto", new ClientRequestDto());
        model.addAttribute("citiesList", Cities.values());
        model.addAttribute("packageList", packageService.findAll());

        return "user-view/get-started/get-started";
    }

    @PostMapping("")
    public String getStartedPost(@Valid @ModelAttribute ClientRequestDto clientRequestDto,
                                 BindingResult br,
                                 RedirectAttributes ra,
                                 HttpSession session,
                                 Model model) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "/user-view/get-started/get-started";
        }

        if (clientRequestRepository.existsByEmail(clientRequestDto.getEmail())) {
            model.addAttribute("emailExists", "Request with this email already exists!");
            model.addAttribute("clientRequestDto", clientRequestDto);

            model.addAttribute("citiesList", Cities.values());
            model.addAttribute("packageList", packageService.findAll());
            return "/user-view/get-started/get-started";
        }

        var randomTicketNr = 100000L + (long) (Math.random() * 900000);
        clientRequestDto.setTicketNr(randomTicketNr);
        clientRequestDto.setStatus(StatusEnum.OPEN);
        session.setAttribute("status", StatusEnum.OPEN);

        var selectedPlan = packageService.findById(clientRequestDto.getSubscriptionPlan().getId());
        clientRequestDto.setSubscriptionPlan(selectedPlan);
        service.add(clientRequestDto);

        ra.addFlashAttribute("successRequest", "Your form has been requested successfully. You will receive a response via email shortly.");
        ra.addFlashAttribute("ticketNr", "Your ticket number is: " + randomTicketNr + "!");
        session.removeAttribute("status");
        return "redirect:/get-started/successfully-sent";
    }

    @GetMapping("/successfully-sent")
    public String successfullySent() {
        return "user-view/get-started/success";
    }


}
