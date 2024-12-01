package org.example.finalproject.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-pw/management")

public class ManagementController {

    @GetMapping("/admins")
    public String manageAdmins() {
        return "admin-view/management/admins/admins";
    }

    @GetMapping("/clients")
    public String manageCustomers() {
        return "admin-view/management/clients/clients";
    }

    @GetMapping("/new-admin")
    public String newAdmin() {
        return "admin-view/management/admins/new";
    }

    @GetMapping("/{id}/details")
    public String details(@PathVariable long id) {
        return "admin-view/management/admins/details";
    }

}
