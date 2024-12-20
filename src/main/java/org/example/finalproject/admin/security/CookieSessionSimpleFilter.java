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

        if (request.getRequestURI().contains("/assets")) {
            filterChain.doFilter(request, response);
        }

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("admin") != null) {
            if (request.getRequestURI().equals("admin-af")) {
                response.sendRedirect("/admin-af/dashboard");
                return;
            }
            Admin admin = (Admin) session.getAttribute("admin");

            filterChain.doFilter(request, response);
            return;
        }


        if (request.getRequestURI().equals("/admin-af")) {
            filterChain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/admin-af?returnUrl=" + request.getRequestURI());


    }
}


