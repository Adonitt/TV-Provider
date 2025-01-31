package org.example.finalproject.user.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.AdminEntity;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public String validateClient(@ModelAttribute SubDto subDto, Model model, HttpSession session) {
        Optional<ClientsEntity> clientOpt = clientsRepository.findByClientNrAndLastName(subDto.getClientNr(), subDto.getLastName());

        if (clientOpt.isPresent()) {
            ClientsEntity client = clientOpt.get();
            session.setAttribute("clientId", client.getId());
            // Map the client entity to SubDetailsDto (if you have a mapper)
            SubDetailsDto subDetailsDto = clientMapper.toSubDetailsDto(client); // You might already have a method like this

            model.addAttribute("subDetails", subDetailsDto);  // Add SubDetailsDto to the model

            return "user-view/e-payment/details"; // Redirect to the details page
        }

        model.addAttribute("errorMessage", "Client does not exist. Please check your details.");
        return "user-view/e-payment/e-payment"; // Stay on the e-payment page
    }

    @GetMapping("{id}/bank-information")
    public String bankInformation(@PathVariable Long id, Model model) {
        var client = service.findClientById(id); // Fetch client details using ID


        model.addAttribute("client", client);
        model.addAttribute("bankInformation", new BankInformation());

        return "user-view/e-payment/bank-information";
    }


    @PostMapping("/{id}/bank-information")
    public String bankInformation(@PathVariable Long id, @ModelAttribute BankInformation bankInformation,
                                  Model model) {

        BankInformation defaultBank = new BankInformation("Fatlind Adonit", "1234123412341234", "12", "24", "123");

        if (!bankInformation.getCardHolder().equals(defaultBank.getCardHolder()) ||
                !bankInformation.getCardNumber().equals(defaultBank.getCardNumber()) ||
                !bankInformation.getExpiryMonth().equals(defaultBank.getExpiryMonth()) ||
                !bankInformation.getExpiryYear().equals(defaultBank.getExpiryYear()) ||
                !bankInformation.getCvc().equals(defaultBank.getCvc())) {

            model.addAttribute("errorMessage", "Please enter valid bank information.");
            return "user-view/e-payment/bank-information";
        }
        service.extendSubscriptionByOneMonth(id);

        return "redirect:/e-payment/" + id + "/invoice"; // Redirect to invoice with client ID
    }


    @GetMapping("/{id}/invoice")
    public String invoice(@PathVariable Long id, Model model) {
        var client = service.findClientById(id);
        model.addAttribute("client", client);
        model.addAttribute("todayDate", LocalDate.now());
        model.addAttribute("subEndDate", client.getSubscriptionEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.addAttribute("contractEndDate", client.getContractExpiryDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "user-view/e-payment/invoice";
    }


}
