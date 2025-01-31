package org.example.finalproject.user.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.user.dtos.sub.SubDetailsDto;
import org.example.finalproject.user.dtos.sub.SubDto;
import org.example.finalproject.user.entities.BankInformation;
import org.example.finalproject.user.entities.ClientsEntity;
import org.example.finalproject.user.mappers.ClientMapper;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.example.finalproject.user.services.impls.ClientRequestServiceImpl;
import org.example.finalproject.user.services.impls.PageViewCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/e-payment")
@RequiredArgsConstructor
public class EpaymentController {

    private final ClientRequestServiceImpl service;
    private final ClientMapper clientMapper;
    private final ClientsRepository clientsRepository;
    private final PageViewCounterService pageViewCounterService;

    @GetMapping("")
    public String ePayment(Model model) {
        model.addAttribute("subDto", new SubDto());
        model.addAttribute("ePaymentCounter", pageViewCounterService.incrementCounter("e-payment"));
        return "user-view/e-payment/e-payment";
    }

    @PostMapping("")
    public String validateClient(@ModelAttribute SubDto subDto, Model model) {
        Optional<ClientsEntity> clientOpt = clientsRepository.findByClientNrAndLastName(subDto.getClientNr(), subDto.getLastName());

        if (clientOpt.isPresent()) {
            ClientsEntity client = clientOpt.get();

            // Map the client entity to SubDetailsDto (if you have a mapper)
            SubDetailsDto subDetailsDto = clientMapper.toSubDetailsDto(client); // You might already have a method like this

            model.addAttribute("subDetails", subDetailsDto);  // Add SubDetailsDto to the model

            return "user-view/e-payment/details"; // Redirect to the details page
        }

        model.addAttribute("errorMessage", "Client does not exist. Please check your details.");
        return "user-view/e-payment/e-payment"; // Stay on the e-payment page
    }

    @GetMapping("/bank-information")
    public String bankInformation(Model model) {
        model.addAttribute("bankInformation", new BankInformation());
        return "user-view/e-payment/bank-information";
    }

    @PostMapping("/bank-information")
    public String bankInformation(@ModelAttribute BankInformation bankInformation, Model model) {

        BankInformation defaultBank = new BankInformation("Fatlind Adonit", "1234123412341234", "12", "24", "123");


        if (!bankInformation.getCardHolder().equals(defaultBank.getCardHolder()) ||
                !bankInformation.getCardNumber().equals(defaultBank.getCardNumber()) ||
                !bankInformation.getExpiryMonth().equals(defaultBank.getExpiryMonth()) ||
                !bankInformation.getExpiryYear().equals(defaultBank.getExpiryYear()) ||
                !bankInformation.getCvc().equals(defaultBank.getCvc())) {

            model.addAttribute("errorMessage", "Please enter valid bank information.");
            return "user-view/e-payment/bank-information"; // Stay on the same page
        }

        return "redirect:/e-payment/invoice";
    }



    @GetMapping("/invoice")
    public String invoice() {
        return "user-view/e-payment/invoice";
    }
}
