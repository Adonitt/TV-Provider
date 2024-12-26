package org.example.finalproject.admin.dtos.admin;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.infrastructure.interfaces.AtLeast18YearsOld;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AdminEditingDto {

    private long id;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be at least 2 characters long and at most 50 characters long")
    private String name;

    @NotNull(message = "Surname is required")
    @NotBlank(message = "Surname is required")
    @Size(min = 2, max = 50, message = "Surname must be at least 2 characters long and at most 50 characters long")
    private String surname;

    @NotNull(message = "Personal number is required")
    @NotBlank(message = "Personal number is required")
    @Size(min = 10, max = 10, message = "Personal number must be 10 digits long")
    private String personalNumber;

    @AtLeast18YearsOld(message = "Admin must be at least 18 years old to register")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Size(min = 9, max = 25, message = "Phone number must be at least 9 characters long and at most 25 characters long")
    private String phoneNumber;

    @NotNull(message = "Country is required")
    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Address is required")
    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "City is required")
    @NotBlank(message = "City is required")
    private String city;

    private int postcode;

    @NotNull(message = "role is required")
    @NotBlank(message = "role is required")
    private String role;

    private String photo;

    private String gender;


}
