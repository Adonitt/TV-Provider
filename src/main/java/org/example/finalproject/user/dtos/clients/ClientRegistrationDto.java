package org.example.finalproject.user.dtos.clients;

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

    private ContractStatus subscriptionStatus;

    @NotNull(message = "Billing address is required")
    @NotBlank(message = "Billing address is required")
    private String billingAddress;

    private LocalDateTime installationDate = LocalDateTime.now();

    @NotNull(message = "Preferred language is required")
    private PreferredLanguages preferredLanguage;

    @NotNull(message = "Device type is required")
    private DevicesTypes deviceType;

    private String registeredBy;

    private LocalDateTime registeredTime = LocalDateTime.now();

    private StatusEnum status;

    private LocalDateTime contractDate;

    @NotNull(message = "Contract status is required")
    private ContractStatus contractStatus;

    private LocalDateTime contractExpiryDate;

    private String notes;
    private String declinedBy;


}
