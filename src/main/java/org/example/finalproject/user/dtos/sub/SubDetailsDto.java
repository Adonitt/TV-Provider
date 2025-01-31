package org.example.finalproject.user.dtos.sub;

import lombok.*;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.user.entities.enums.Cities;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubDetailsDto {

    private Long id;

    private Long clientNr;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Cities city;

    private String address;

    private PackageEntity subscriptionPlan;

    private LocalDateTime subscriptionEndDate;
}
