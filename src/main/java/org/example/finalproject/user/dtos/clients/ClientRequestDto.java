package org.example.finalproject.user.dtos.clients;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.admin.models.admin.PackageEnum;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.StatusEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {

    private Long id;

    private Long ticketNr;

    @NotBlank(message = "First name is required.")
    @Size(min = 2, max = 50, message = "First name must not exceed 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(min = 2, max = 50, message = "Last name must not exceed 50 characters.")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Pattern(
            regexp = "^\\+?[0-9\\-\\s]{7,15}$",
            message = "Invalid phone number. Must be between 7 and 15 digits, and may include '+' or '-' symbols."
    )
    @Size(min = 7, max = 15, message = "Phone number must not exceed 15 characters.")
    private String phone;

    @Enumerated
    private Cities city;

    @NotBlank(message = "Address is required.")
    @Size(max = 255, message = "Address must not exceed 255 characters.")
    private String address;

    private LocalDateTime requestTime = LocalDateTime.now();

    private StatusEnum status;

    private PackageEntity subscriptionPlan;

    private String declinedBy;


}
