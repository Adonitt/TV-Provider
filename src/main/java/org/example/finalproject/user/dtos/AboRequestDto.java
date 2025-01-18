package org.example.finalproject.user.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.finalproject.user.entities.enums.Cities;
import org.example.finalproject.user.entities.enums.Offers;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AboRequestDto {

    @PositiveOrZero(message = "ID must be a positive number")
    private Long id;

    @NotNull(message = "First name is required")
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be at least 2 characters long and at most 50 characters long")
    private String firstName;

    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be at least 2 characters long and at most 50 characters long")
    private String lastName;

    @Email(message = "Invalid email address")
    private String email;

    @Size(min = 7, max = 15, message = "Phone number must be at least 7 characters long and at most 15 characters long")
    private String phone;

    @NotNull(message = "City is required")
    @NotBlank(message = "City is required")
    private Cities cities;

    private Offers offers;

    @NotNull(message = "Address is required")
    @NotBlank(message = "Address is required")
    @Size(min = 2, max = 255, message = "Address must be at least 2 characters long and at most 255 characters long")
    private String address;


    private LocalDateTime requestTime = LocalDateTime.now();


}
