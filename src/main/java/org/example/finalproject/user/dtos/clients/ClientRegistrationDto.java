package org.example.finalproject.user.dtos.clients;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.user.entities.enums.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientRegistrationDto {
    private Long id;

    private LocalDateTime subscriptionStartDate;

    private LocalDateTime subscriptionEndDate;

    @Enumerated(EnumType.STRING)
    private ContractStatus subscriptionStatus;

    @NotNull(message = "Billing address is required")
    @NotBlank(message = "Billing address is required")
    private String billingAddress;

    private LocalDateTime installationDate = LocalDateTime.now();

    @NotNull(message = "Preferred language is required")
    @Enumerated(EnumType.STRING)
    private PreferredLanguages preferredLanguage;

    @NotNull(message = "Device type is required")
    @Enumerated(EnumType.STRING)
    private DevicesTypes deviceType;

    private String registeredBy;

    private LocalDateTime registeredTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private LocalDateTime contractDate;

    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;

    private LocalDateTime contractExpiryDate;

    private String notes;
    private String declinedBy;


}
