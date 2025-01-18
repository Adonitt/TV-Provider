package org.example.finalproject.admin.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class CookieSessionSimpleFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().contains("/assets")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            if (request.getRequestURI().equals("admin-panel")) {
                response.sendRedirect("/admin-panel/dashboard");
                return;
            }

            AdminEntity admin = (AdminEntity) session.getAttribute("admin");
            if ((admin.getRole().equalsIgnoreCase("Admin") && request.getRequestURI().endsWith("/edit"))
                    || (admin.getRole().equalsIgnoreCase("Admin") && request.getRequestURI().endsWith("/delete"))
                    || (admin.getRole().equalsIgnoreCase("Admin") && request.getRequestURI().endsWith("/new"))
            ) {
                response.sendRedirect("/admin-panel/dashboard");
            }

            filterChain.doFilter(request, response);
            return;
        }


        if (request.getRequestURI().equals("/admin-panel")
                || request.getRequestURI().equals("/admin-panel/forgot-password")
                || request.getRequestURI().equals("/admin-panel/send-reset-link")
                || request.getRequestURI().equals("/")
                || request.getRequestURI().equals("/about")
                || request.getRequestURI().equals("/contact")
                || request.getRequestURI().equals("/services")
                || request.getRequestURI().equals("/tv-channels")
                || request.getRequestURI().equals("/pricing")
                || request.getRequestURI().equals("/e-payment")
                || request.getRequestURI().equals("/get-started")
                || request.getRequestURI().equals("/e-payment/bank-information")
                || request.getRequestURI().equals("/e-payment/invoice")
                || request.getRequestURI().equals("/get-started/successfully-sent")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/admin-panel?returnUrl=" + request.getRequestURI());


    }
}


