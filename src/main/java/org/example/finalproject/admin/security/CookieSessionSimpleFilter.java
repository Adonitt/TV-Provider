package org.example.finalproject.admin.security;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.example.finalproject.admin.models.admin.AdminRole;
import org.example.finalproject.user.entities.ClientRequestEntity;
import org.example.finalproject.user.entities.ClientsEntity;
import org.example.finalproject.user.entities.enums.StatusEnum;
import org.example.finalproject.user.repositories.ClientRequestRepository;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Configuration
public class CookieSessionSimpleFilter extends OncePerRequestFilter {


    private final ClientRequestRepository clientRequestRepository;
    private final ClientsRepository clientsRepository;

    public CookieSessionSimpleFilter(ClientRequestRepository clientRequestRepository, ClientsRepository clientsRepository) {
        this.clientRequestRepository = clientRequestRepository;
        this.clientsRepository = clientsRepository;
    }

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
            if ((admin.getRole().equals(AdminRole.ADMIN) && request.getRequestURI().endsWith("/edit"))
                    || (admin.getRole().equals(AdminRole.ADMIN) && request.getRequestURI().endsWith("/delete"))
                    || (admin.getRole().equals(AdminRole.ADMIN) && request.getRequestURI().endsWith("/new"))
            ) {
                response.sendRedirect("/admin-panel/dashboard");
            }

            String requestURI = request.getRequestURI();

            if (requestURI.endsWith("/save-client")) {
                Optional<Long> clientIdOpt = extractClientIdFromRequestURI(requestURI);

                if (clientIdOpt.isPresent()) {
                    Long clientId = clientIdOpt.get();
                    Optional<ClientRequestEntity> clientOpt = clientRequestRepository.findById(clientId);

                    if (clientOpt.isPresent()) {
                        ClientRequestEntity clientRequest = clientOpt.get();
                        StatusEnum status = clientRequest.getStatus();

                        if (status == StatusEnum.DECLINED || status == StatusEnum.SAVED) {
                            response.sendRedirect("/admin-panel/management/clients?error=access-denied");
                            return;
                        }

                        if (status == StatusEnum.OPEN) {
                            filterChain.doFilter(request, response);
                            return;
                        }
                    } else {
                        response.sendRedirect("/admin-panel/management/clients?error=not-found");
                        return;
                    }
                }
            }

            filterChain.doFilter(request, response);
            return;
        }


        if (request.getRequestURI().equals("/admin-panel") || request.getRequestURI().equals("/admin-panel/forgot-password") || request.getRequestURI().equals("/admin-panel/send-reset-link")
                || request.getRequestURI().
                equals("/")
                || request.getRequestURI().
                equals("/about")
                || request.getRequestURI().
                equals("/contact")
                || request.getRequestURI().
                equals("/services")
                || request.getRequestURI().
                equals("/tv-channels")
                || request.getRequestURI().
                equals("/pricing")
                || request.getRequestURI().
                equals("/e-payment")
                || request.getRequestURI().
                equals("/get-started")
                || request.getRequestURI().
                equals("/e-payment/bank-information")
                || request.getRequestURI().
                equals("/e-payment/invoice")
                || request.getRequestURI().
                equals("/get-started/successfully-sent")
                || request.getRequestURI().
                equals("/admin-panel/website-details")
                || request.getRequestURI().
                equals("/admin-panel/management/clients/create")
                || request.getRequestURI().
                equals("/e-payment/bank-information")
                || request.getRequestURI().
                equals("/e-payment/invoice")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        response.sendRedirect("/admin-panel?returnUrl=" + request.getRequestURI());


    }

    private Optional<Long> extractClientIdFromRequestURI(String requestURI) {
        try {
            String[] parts = requestURI.split("/");
            return Optional.of(Long.parseLong(parts[parts.length - 2]));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

}


