package org.example.finalproject.admin.controllers.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-af/management")
public class ReqForNewClientsController {
    @GetMapping("/req-for-new-clients")
    public String reqForNewClients() {

        return "admin-view/management/requests-for-new-clients/req-for-new-clients-list";
    }
}
