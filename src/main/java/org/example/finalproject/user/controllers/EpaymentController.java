package org.example.finalproject.user.controllers;

import org.example.finalproject.user.dtos.sub.SubDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/e-payment")
public class EpaymentController {

    @GetMapping("")
    public String ePayment(Model model) {
        model.addAttribute("subDto", new SubDto());
        return "user-view/e-payment/e-payment";
    }

    @PostMapping("")
    public String ePayment(@ModelAttribute SubDto subDto, Model model) {
        model.addAttribute("message", "Client founded successfully. Please fill in the bank credentials.");
        return "user-view/e-payment/e-payment";
    }


    @GetMapping("/bank-information")
    public String bankInformation() {
        return "user-view/e-payment/bank-information";
    }

    @GetMapping("/invoice")
    public String invoice() {
        return "user-view/e-payment/invoice";
    }


}
