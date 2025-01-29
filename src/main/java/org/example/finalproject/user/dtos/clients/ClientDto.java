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


    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Cities city;

    private String address;

    private LocalDateTime requestTime = LocalDateTime.now();

    private PackageEntity subscriptionPlan;

    private LocalDateTime subscriptionStartDate;

    private LocalDateTime subscriptionEndDate;

    private ContractStatus subscriptionStatus;

    private String billingAddress;

    private LocalDateTime installationDate = LocalDateTime.now();

    private PreferredLanguages preferredLanguage;

    private DevicesTypes deviceType;

    private String registeredBy;

    private LocalDateTime registeredTime = LocalDateTime.now();

    private StatusEnum status;

    private LocalDateTime contractDate;

    private ContractStatus contractStatus;

    private LocalDateTime contractExpiryDate;

    private String notes;

    private String declinedBy;

    private String modifiedBy;

    private LocalDateTime modifiedTime;

    private String reactivatedBy;

    private LocalDateTime reactivatedTime;


}
