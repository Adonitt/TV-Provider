package org.example.finalproject.user.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.dtos.clients.ClientRequestDto;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.StatusEnum;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/get-started")
@RequiredArgsConstructor
public class GetStartedController {

    private final ClientRequestService service;

    @GetMapping("")
    public String getStarted(Model model) {
        model.addAttribute("clientRequestEntity", new ClientRequestDto());
        model.addAttribute("citiesList", Cities.values());
        model.addAttribute("packageList", PackageEnum.values());
        return "user-view/get-started/get-started";
    }

    @PostMapping("")
    public String getStartedPost(@Valid @ModelAttribute ClientRequestDto clientRequest, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "/user-view/get-started/get-started";
        }
        var randomTicketNr = 100000L + (long) (Math.random() * 900000);
        clientRequest.setTicketNr(randomTicketNr);
        clientRequest.setStatus(StatusEnum.OPEN);
        service.add(clientRequest);

        ra.addFlashAttribute("successRequest", "Your form has been requested successfully. You will receive a response via email shortly.");
        ra.addFlashAttribute("ticketNr", "Your ticket number is: " + randomTicketNr + "!");
        return "redirect:/get-started/successfully-sent";
    }

    @GetMapping("/successfully-sent")
    public String successfullySent() {
        return "user-view/get-started/success";
    }


}
