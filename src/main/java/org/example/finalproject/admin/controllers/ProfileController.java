package org.example.finalproject.admin.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.admins.AdminLoginDto;
import org.example.finalproject.admin.helpers.files.FileHelper;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.example.finalproject.admin.repositories.AdminRepository;
import org.example.finalproject.admin.services.interfaces.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin-panel")
@RequiredArgsConstructor
public class ProfileController {
    private final AdminRepository adminRepository;
    private final AdminService service;
    private final FileHelper fileHelper;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/profile")
    public String profile() {
        return "admin-view/profile/profile";
    }

    @GetMapping("/profile/change-password")
    public String changePassword(@ModelAttribute AdminEntity admin, RedirectAttributes ra, Model model) {
        model.addAttribute("adminId", admin.getId());
        return "admin-view/profile/change-password";
    }

    @PostMapping("/profile/change-password")
    public String changePassword1(@RequestParam("currentPassword") String currentPassword,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  @RequestParam("adminId") Long adminId,
                                  RedirectAttributes ra) {

        AdminEntity existingAdmin = adminRepository.findById(adminId).orElse(null);
        if (existingAdmin == null) {
            ra.addFlashAttribute("adminNotFound", "Admin not found!");
            return "redirect:/admin-panel/changePassword";
        }
        System.out.printf("Admin ID: %d%n - Email : %s %n Current Password: %s %n- New Password: %s%n", adminId, existingAdmin.getEmail(), existingAdmin.getPassword(), newPassword);

        if (!passwordEncoder.matches(currentPassword, existingAdmin.getPassword())) {
            ra.addFlashAttribute("currentPassNotCorrect", "Current password is not correct!");
            return "redirect:/admin-panel/profile/change-password";
        }

        if (!newPassword.equals(confirmPassword)) {
            ra.addFlashAttribute("passwordNotMatch", "New password and confirm password do not match!");
            return "redirect:/admin-panel/profile/change-password";
        }

        service.changePassword(adminId, newPassword);
        ra.addFlashAttribute("successChangeMessage", "Password changed successfully!");
        return "redirect:/admin-panel/profile";
    }

    @PostMapping("/profile/send-reset-link")
    public String sendResetLink(@ModelAttribute AdminLoginDto adminLoginDto, RedirectAttributes ra) {
        ra.addFlashAttribute("successMessage", "A link for resetting your password has been sent to your email! Please check your inbox.");
        return "redirect:/admin-panel/profile";
    }

    @GetMapping("/profile/edit-profile")
    public String editProfile(@SessionAttribute("admin") AdminEntity admin, Model model) {
        model.addAttribute("admin", admin);
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("from", LocalDate.now().minusYears(65));

        return "admin-view/profile/edit-profile";
    }

    @PostMapping("/profile/edit-profile")
    public String editProfile(@ModelAttribute AdminEntity admin,
                              @RequestParam("photoFile") MultipartFile photoFile,
                              @SessionAttribute("admin") AdminEntity sessionAdmin,
                              RedirectAttributes ra) throws IOException {

        if (!photoFile.isEmpty()) {
            uploadPhoto(sessionAdmin, photoFile);
        }

        sessionAdmin.setName(admin.getName());
        sessionAdmin.setSurname(admin.getSurname());
        sessionAdmin.setPersonalNumber(admin.getPersonalNumber());
        sessionAdmin.setDateOfBirth(admin.getDateOfBirth());
        sessionAdmin.setPhoneNumber(admin.getPhoneNumber());
        sessionAdmin.setGender(admin.getGender());
        sessionAdmin.setCountry(admin.getCountry());
        sessionAdmin.setAddress(admin.getAddress());
        sessionAdmin.setPostcode(admin.getPostcode());
        sessionAdmin.setCity(admin.getCity());

        adminRepository.save(sessionAdmin);

        ra.addFlashAttribute("successMessage", "Profile edited successfully!");
        return "redirect:/admin-panel/profile";
    }

    private void uploadPhoto(AdminEntity admin, MultipartFile photoFile) {
        try {
            String fileName = fileHelper.uploadFile("target/classes/static/assets-a/img",
                    photoFile.getOriginalFilename(),
                    photoFile.getBytes());
            admin.setPhoto("/assets-a/img/" + fileName);
        } catch (Exception e) {
            System.out.println("Error uploading photo: " + e.getMessage());
        }
    }

}
