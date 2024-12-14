package org.example.finalproject.user.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/e-payment")
public class EpaymentController {
    @GetMapping("")
    public String ePayment() {
        return "user-view/e-payment/e-payment";
    }
}
