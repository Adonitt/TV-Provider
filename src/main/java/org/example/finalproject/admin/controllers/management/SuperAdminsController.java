package org.example.finalproject.admin.controllers.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin-af/management")
public class SuperAdminsController {
    @GetMapping("/super-admins")
    public String superAdmins() {
        return "admin-view/management/super-admins/super-admins-list";
    }

    @GetMapping("/new-s-admin")
    public String newAdmin() {
        return "admin-view/management/super-admins/new";
    }

    @GetMapping("/sa/{id}/details")
    public String details(@PathVariable long id) {
        return "admin-view/management/super-admins/details";
    }
}
