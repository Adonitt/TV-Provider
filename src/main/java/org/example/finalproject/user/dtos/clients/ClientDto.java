package org.example.finalproject.user.dtos.clients;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.user.entities.enums.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClientDto {


    private Long id;

    private Long ticketNr;

    private Long clientNr;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,20}$", message = "Invalid phone number format")
    private String phone;

    @NotNull(message = "City cannot be null")
    private Cities city;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    private LocalDateTime requestTime = LocalDateTime.now();

    @NotNull(message = "Subscription plan cannot be null")
    private PackageEntity subscriptionPlan;

    @FutureOrPresent(message = "Subscription start date must be in the present or future")
    private LocalDateTime subscriptionStartDate;

    @Future(message = "Subscription end date must be in the future")
    private LocalDateTime subscriptionEndDate;

    private ContractStatus subscriptionStatus;

    @NotBlank(message = "Billing address cannot be blank")
    private String billingAddress;

    private LocalDateTime installationDate = LocalDateTime.now();

    @NotNull(message = "Preferred language cannot be null")
    private PreferredLanguages preferredLanguage;

    @NotNull(message = "Device type cannot be null")
    private DevicesTypes deviceType;

    private String registeredBy;

    private LocalDateTime registeredTime = LocalDateTime.now();

    private StatusEnum status;

    @PastOrPresent
    private LocalDateTime contractStartDate;

    private ContractStatus contractStatus;

    private LocalDateTime contractExpiryDate;

    private String notes;

    private String declinedBy;

    private String modifiedBy;

    private LocalDateTime modifiedTime;

    private String reactivatedBy;

    private LocalDateTime reactivatedTime;
}

