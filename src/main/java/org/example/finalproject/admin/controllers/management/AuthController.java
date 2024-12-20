package org.example.finalproject.admin.controllers.management;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.services.AdminLoginService;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin-af")

public class AuthController {
    private final AdminLoginService adminLoginService;
    private final ResourceLoader resourceLoader;

    public AuthController(AdminLoginService adminLoginService, ResourceLoader resourceLoader) {
        this.adminLoginService = adminLoginService;
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("")
    public String login(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin-view/auths/login";
    }

    @PostMapping("")
    public String login(@ModelAttribute Admin admin,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "returnUrl", required = false) String returnUrl) {

        var searchAdmin = adminLoginService.login(admin.getEmail(), admin.getPassword());

        if (searchAdmin == null) {
            return "redirect:/admin-af";
        }
        Cookie cookie = new Cookie("adminId", String.valueOf(searchAdmin.getId()));
        cookie.setMaxAge(60 * 60 * 24); // 1 day
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setDomain("localhost");
        response.addCookie(cookie);

        HttpSession session = request.getSession();
        session.setAttribute("admin", searchAdmin);
        session.setAttribute("role", searchAdmin.getRole());

        if (returnUrl != null){
            return "redirect:" +returnUrl;
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
    public String forgotPassword() {

        return "admin-view/auths/forgot-password";
    }

}
