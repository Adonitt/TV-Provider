package org.example.finalproject.user.dtos.clients;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.user.entities.enums.DevicesTypes;
import org.example.finalproject.user.entities.enums.PreferredLanguages;
import org.example.finalproject.user.entities.enums.StatusEnum;

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

    @Enumerated
    private PreferredLanguages preferredLanguage;

    @Enumerated
    private DevicesTypes deviceType;

    private String registeredBy;

    private LocalDateTime registeredTime = LocalDateTime.now();

    private StatusEnum status ;

}
