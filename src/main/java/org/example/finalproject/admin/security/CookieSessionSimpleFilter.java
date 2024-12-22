package org.example.finalproject.admin.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.finalproject.admin.models.admin.Admin;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class CookieSessionSimpleFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            if (request.getRequestURI().equals("admin-af")) {
                response.sendRedirect("/admin-af/dashboard");
                return;
            }

            Admin admin = (Admin) session.getAttribute("admin");
            if ((admin.getRole().equalsIgnoreCase("Admin") && request.getRequestURI().endsWith("/edit"))
                    || (admin.getRole().equalsIgnoreCase("Admin") && request.getRequestURI().endsWith("/delete"))) {
                response.sendRedirect("/admin-af/management/admins");
            }

            filterChain.doFilter(request, response);
            return;
        }


        if (request.getRequestURI().equals("/admin-af")
                || request.getRequestURI().equals("/admin-af/forgot-password")
                || request.getRequestURI().equals("/")
                || request.getRequestURI().equals("/about")
                || request.getRequestURI().equals("/contact")
                || request.getRequestURI().equals("/services")
                || request.getRequestURI().equals("/tv-channels")
                || request.getRequestURI().equals("/pricing")
                || request.getRequestURI().equals("/e-payment")
                || request.getRequestURI().equals("/get-started")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/admin-af?returnUrl=" + request.getRequestURI());


    }
}


