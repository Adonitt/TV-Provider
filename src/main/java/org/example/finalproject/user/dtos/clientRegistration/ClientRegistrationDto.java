package org.example.finalproject.user.dtos.clientRegistration;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.PreferredLanguages;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientRegistrationDto {
    private Long id;

    private Long ticketNr;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Cities city;

    private String address;

    private LocalDateTime requestTime = LocalDateTime.now();


    @NotNull(message = "Subscription plan is required")
    @NotBlank(message = "Subscription plan is required")
    private PackageEntity subscriptionPlan;

    private boolean subscriptionActive;

    @NotNull(message = "Billing address is required")
    @NotBlank(message = "Billing address is required")
    private String billingAddress;

    @NotNull(message = "Installation date is required")
    @NotBlank(message = "Installation date is required")
    private LocalDateTime installationDate;

    @NotNull(message = "Preferred language is required")
    @NotBlank(message = "Preferred language is required")
    private PreferredLanguages preferredLanguage;

    @NotNull(message = "Device type is required")
    @NotBlank(message = "Device type is required")
    private String deviceType;

}
