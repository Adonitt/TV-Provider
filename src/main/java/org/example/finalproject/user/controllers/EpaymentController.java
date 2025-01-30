package org.example.finalproject.user.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.user.dtos.sub.SubDetailsDto;
import org.example.finalproject.user.dtos.sub.SubDto;
import org.example.finalproject.user.entities.ClientsEntity;
import org.example.finalproject.user.mappers.ClientMapper;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/e-payment")
@RequiredArgsConstructor
public class EpaymentController {

    private final ClientsRepository clientsRepository;
    private final ClientMapper clientMapper;

    @GetMapping("")
    public String ePayment(Model model) {
        model.addAttribute("subDto", new SubDto()); // Empty DTO for input
        return "user-view/e-payment/e-payment";
    }

    @PostMapping("")
    public String validateClient(@ModelAttribute SubDto subDto, Model model) {
        Optional<ClientsEntity> clientOpt = clientsRepository.findByClientNrAndLastName(subDto.getClientNr(), subDto.getLastName());

        if (clientOpt.isPresent()) {
            // You could choose to populate the SubDto with additional data here
            ClientsEntity client = clientOpt.get();
            subDto.setLastName(client.getLastName());

            // Add subDto to the model
            model.addAttribute("subDto", subDto);

            // Redirect to details page
            return "user-view/e-payment/details";
        }

        model.addAttribute("errorMessage", "Client does not exist. Please check your details.");
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
