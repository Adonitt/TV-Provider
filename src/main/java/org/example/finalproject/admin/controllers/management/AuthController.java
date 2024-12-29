package org.example.finalproject.admin.controllers.management;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.AdminLoginDto;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.repositories.AdminRepository;
import org.example.finalproject.admin.services.interfaces.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin-af")
@RequiredArgsConstructor
public class AuthController {
    private final AdminService service;
    private final AdminRepository adminRepository;

    @GetMapping("")
    public String login(Model model) {
        model.addAttribute("adminLoginDto", new AdminLoginDto());
        return "admin-view/auths/login";
    }

    @PostMapping("")
    public String login(@Valid @ModelAttribute AdminLoginDto adminLoginDto,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "returnUrl", required = false) String returnUrl,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "admin-view/auths/login";
        }

        var searchAdmin = service.login(adminLoginDto.getEmail(), adminLoginDto.getPassword());

        if (searchAdmin == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid email or password");
            return "redirect:/admin-af";
        }

        Cookie cookie = new Cookie("adminId", String.valueOf(searchAdmin.getId()));
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setDomain("localhost");
        response.addCookie(cookie);

        HttpSession session = request.getSession();
        session.setAttribute("admin", searchAdmin);

        if (returnUrl != null) {
            return "redirect:" + returnUrl;
        }

        return "redirect:/admin-af/dashboard";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("adminId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/admin-af";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(@ModelAttribute Admin admin, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
        }

        if (admin.getEmail() != null) {
            System.out.println("Searching for email: " + admin.getEmail());

            Admin foundAdmin = adminRepository.findByEmail(admin.getEmail()).orElse(null);

            if (foundAdmin != null) {
                redirectAttributes.addFlashAttribute("successMessage", "Password reset link has been sent to your email. Please check your inbox!");
                return "redirect:/admin-af";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Email not found");
                return "redirect:/admin-af/forgot-password";
            }
        }
        return "admin-view/auths/forgot-password";
    }

}
