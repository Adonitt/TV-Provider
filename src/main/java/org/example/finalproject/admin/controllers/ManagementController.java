package org.example.finalproject.controllers.admin_controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/management")
public class ManagementController {


    @GetMapping("/users")
    public String manageUsers() {
        return "/admin-view/management/users";
    }

    @GetMapping("/admins")
    public String manageAdmins() {
        return "/admin-view/management/admins";
    }


}
