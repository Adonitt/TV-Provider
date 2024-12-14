package org.example.finalproject.admin.controllers.management;

import jakarta.validation.Valid;
import org.example.finalproject.admin.helpers.files.FileHelper;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin-af/management/admins")
public class AdminController {

    private final AdminService adminService;
    private final FileHelper fileHelper;

    public AdminController(AdminService adminService, FileHelper fileHelper) {
        this.adminService = adminService;
        this.fileHelper = fileHelper;
    }


    @GetMapping("")
    public String manageAdmins(Model model) {
        List<Admin> admins = adminService.findAll();

        // Calculate age for each admin
        for (Admin admin : admins) {
            admin.setAge(admin.calculateAge());  // Set the calculated age
        }
        model.addAttribute("admins", adminService.findAll());
        return "/admin-view/management/admins/admins-list";
    }


    @GetMapping("/new-admin")
    public String newAdmin(Model model) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("from", LocalDate.now().minusYears(65));
        return "admin-view/management/admins/new";
    }

    @PostMapping("/new-admin")
    public String newAdmin(@Valid @ModelAttribute Admin admin, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           @RequestParam("photoFile") MultipartFile photoFile) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "admin-view/management/admins/new";
        }
        System.out.println("Photo file: " + photoFile.getOriginalFilename());

        redirectAttributes.addFlashAttribute("successMessage", "Admin added successfully!");

        if (!photoFile.isEmpty()) {
            uploadPhoto(admin, photoFile);
        } else {
            admin.setPhoto("/images/admin.jpg");
        }
        adminService.save(admin);
        return "redirect:/admin-pw/management/admins";
    }


    @GetMapping("/{id}/details")
    public String details(@PathVariable long id, Model model) {
        Admin admin = adminService.findById(id);
        admin.calculateAge();
        model.addAttribute("admin", adminService.findById(id));
        return "admin-view/management/admins/details";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("admin", adminService.findById(id));
        return "admin-view/management/admins/edit";
    }

    @PostMapping("/{id}/edit")
    public String modifyAdmin(@ModelAttribute Admin admin, BindingResult bindingResult, @RequestParam("photoFile") MultipartFile photoFile, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "/admin-view/management/admins/edit";
        }
        if (!photoFile.isEmpty()) {
            uploadPhoto(admin, photoFile);
        }
        ra.addFlashAttribute("editedMessage", "Admin with ID: " + admin.getId() + " - modified successfully!");
        adminService.modify(admin);
        return "redirect:/admin-pw/management/admins";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id, Model model) {
        model.addAttribute("admin", adminService.findById(id));
        return "admin-view/management/admins/delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteAdmin(@PathVariable long id, RedirectAttributes ra) {
        ra.addFlashAttribute("deletedMessage", "Admin deleted successfully!");
        adminService.deleteById(id);
        return "redirect:/admin-pw/management/admins";
    }

    private void uploadPhoto(@ModelAttribute @Valid Admin admin, @RequestParam("photoFile") MultipartFile photoFile) {
        try {
            var fileName = fileHelper.uploadFile("target/classes/static/assets-a/img", photoFile.getOriginalFilename(), photoFile.getBytes());
            admin.setPhoto("/assets-a/img/" + fileName);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


}
