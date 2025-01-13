package org.example.finalproject.user.controllers;

import jakarta.validation.Valid;
import org.example.finalproject.user.dtos.AboDto;
import org.example.finalproject.user.models.Client;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/e-payment")
public class EpaymentController {
    @GetMapping("")
    public String ePayment() {
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
