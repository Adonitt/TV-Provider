package org.example.finalproject.user.dtos.clients;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.user.entities.enums.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientRegistrationDto {
    private Long id;

    private boolean subscriptionActive;

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

    @NotNull(message = "Contract type is required")
    private ContractType contractType;

    private LocalDateTime contractDate;

    @NotNull(message = "Contract status is required")
    private ContractStatus contractStatus;

    private LocalDateTime expiryDate;

    private String notes;
    private String declinedBy;


}
