package org.example.finalproject.user.controllers;

import jakarta.validation.Valid;
import org.example.finalproject.user.dtos.AboRequestDto;
import org.example.finalproject.user.models.ClientRequestEntity;
import org.example.finalproject.user.models.enums.Cities;
import org.example.finalproject.user.models.enums.Offers;
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
public class GetStartedController {
    @GetMapping("")
    public String getStarted(Model model) {
        model.addAttribute("aboRequestDto", new AboRequestDto());
        model.addAttribute("citiesList", Cities.values());
        model.addAttribute("offersList", Offers.values());
        return "user-view/get-started/get-started";
    }

    @PostMapping("")
    public String getStartedPost(@Valid @ModelAttribute ClientRequestEntity clientRequest, BindingResult br, RedirectAttributes ra) {
        if (br.hasErrors()) {
            br.getAllErrors().forEach(System.out::println);
            return "/user-view/get-started/get-started";
        }

        ra.addFlashAttribute("successRequest", "Your request has been sent successfully!");

        System.out.println("Recived client: " + clientRequest);
        return "redirect:/";
    }


}
