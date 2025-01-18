package org.example.finalproject.admin.controllers.management;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel/management")
@RequiredArgsConstructor
public class ReqForNewClientsController {
    private final ClientRequestService service;

    @GetMapping("/req-for-new-clients")
    public String reqForNewClients(Model model) {
        model.addAttribute("clientRequestList", service.findAll());
        return "admin-view/management/requests-for-new-clients/req-for-new-clients-list";
    }

    @GetMapping("/req-for-new-clients/{id}/details")
    public String details(@PathVariable long id, Model model) {
        model.addAttribute("clientRequest", service.findById(id));
        return "admin-view/management/requests-for-new-clients/details";
    }


}
