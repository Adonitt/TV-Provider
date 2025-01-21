package org.example.finalproject.user.dtos.clients;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.DevicesTypes;
import org.example.finalproject.user.entities.enums.PreferredLanguages;
import org.example.finalproject.user.entities.enums.StatusEnum;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClientDto {

    private Long id;

    private Long ticketNr;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Cities city;

    private String address;

    private LocalDateTime requestTime = LocalDateTime.now();

    private PackageEntity subscriptionPlan;

    private boolean subscriptionActive;

    private String billingAddress;

    private LocalDateTime installationDate = LocalDateTime.now();

    private PreferredLanguages preferredLanguage;

    private DevicesTypes deviceType;

    private String registeredBy;

    private LocalDateTime registeredTime = LocalDateTime.now();

    private StatusEnum status;

}
