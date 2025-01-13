package org.example.finalproject.user.controllers;

import jakarta.validation.Valid;
import org.example.finalproject.user.models.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/get-started")
public class GetStartedController {
    @GetMapping("")
    public String getStarted() {
        return "user-view/get-started/get-started";
    }

    @PostMapping("")
    public String getStartedPost(@Valid @ModelAttribute Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "/get-started";
        }
        System.out.println("Recived client: " + client);

        return "redirect:/";
    }
}
