package org.example.finalproject.admin.controllers.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel/management/clients")
public class ClientController {

    @GetMapping("")
    public String clients() {
        
        return "admin-view/management/clients/clients-list";
    }

    @GetMapping("/new")
    public String newClient() {

        return "admin-view/management/clients/new-client";
    }

    @GetMapping("{id}/details")
    public String details() {

        return "admin-view/management/clients/details";
    }
}
