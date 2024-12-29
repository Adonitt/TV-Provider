package org.example.finalproject.admin.controllers.management;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.AdminDetailsDto;
import org.example.finalproject.admin.dtos.admin.AdminEditingDto;
import org.example.finalproject.admin.dtos.admin.AdminRegistrationRequestDto;
import org.example.finalproject.admin.helpers.files.FileHelper;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.services.interfaces.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin-af/management/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;
    private final FileHelper fileHelper;

    @GetMapping("")
    public String manageAdmins(Model model) {
        model.addAttribute("adminsList", service.findAll());
        return "/admin-view/management/admins/admins-list";
    }


    @GetMapping("/new-admin")
    public String newAdmin(Model model) {
        model.addAttribute("adminRegistrationRequestDto", new AdminRegistrationRequestDto());
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("from", LocalDate.now().minusYears(65));
        return "admin-view/management/admins/new";
    }

    @PostMapping("/new-admin")
    public String newAdmin(@Valid @ModelAttribute AdminRegistrationRequestDto adminRegistrationRequestDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           @RequestParam("photoFile") MultipartFile photoFile,
                           @SessionAttribute("admin") Admin adminSession) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "admin-view/management/admins/new";
        }

        System.out.println("Photo file: " + photoFile.getOriginalFilename());

        if (!photoFile.isEmpty()) {
            try {
                var fileName = fileHelper.uploadFile("target/classes/static/assets-a/img", photoFile.getOriginalFilename(), photoFile.getBytes());
                adminRegistrationRequestDto.setPhoto("/assets-a/img/" + fileName);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            adminRegistrationRequestDto.setPhoto("/images/admin.jpg");
        }


        redirectAttributes.addFlashAttribute("successMessage", "Admin added successfully!");
        service.add(adminRegistrationRequestDto);
        return "redirect:/admin-af/management/admins";
    }


    @GetMapping("/{id}/details")
    public String etails(@PathVariable long id, Model model) {

        AdminDetailsDto adminDetailsDto = service.findById(id);
        adminDetailsDto.calculateAge();

        model.addAttribute("admin", service.findById(id));
        return "admin-view/management/admins/details";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("adminEditingDto", service.findById(id));
        return "admin-view/management/admins/edit";
    }

    @PostMapping("/{id}/edit")
    public String modifyAdmin(@Valid @ModelAttribute AdminEditingDto adminEditingDto,
                              BindingResult bindingResult,
                              @RequestParam("photoFile") MultipartFile photoFile,
                              RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "admin-view/management/admins/edit";
        }

        if (!photoFile.isEmpty()) {
            try {
                var fileName = fileHelper.uploadFile("target/classes/static/assets-a/img", photoFile.getOriginalFilename(), photoFile.getBytes());
                adminEditingDto.setPhoto("/assets-a/img/" + fileName);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        ra.addFlashAttribute("editedMessage", "Admin with ID: " + adminEditingDto.getId() + " - modified successfully!");
        service.modify(adminEditingDto, adminEditingDto.getId());
        return "redirect:/admin-af/management/admins";
    }


    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id, Model model) {
        model.addAttribute("admin", service.findById(id));
        return "admin-view/management/admins/delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteAdmin(@PathVariable long id, RedirectAttributes ra) {
        service.removeById(id);
        ra.addFlashAttribute("deletedMessage", "Admin deleted successfully!");
        return "redirect:/admin-af/management/admins";
    }


}
