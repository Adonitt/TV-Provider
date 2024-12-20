package org.example.finalproject.admin.dtos.admin;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.infrastructure.interfaces.AtLeast18YearsOld;
import org.example.finalproject.admin.infrastructure.interfaces.SameAs;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdminRegistrationRequestDto {
    @Size(min = 2, max = 50, message = "Name must be at least 2 characters long and at most 50 characters long")
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one number")
    private String password;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one number")
    @SameAs(field = "password", message = "Passwords do not match")
    private String confirmPassword;

    @Size(min = 2, max = 50, message = "Name must be at least 2 characters long and at most 50 characters long")
    @NotBlank(message = "Name is required")
    private String surname;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @AtLeast18YearsOld()
    private LocalDate dateOfBirth;

    @Size(min = 9, max = 25, message = "Phone number must be at least 9 characters long and at most 25 characters long")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    private int postcode;

    private int age;

    private String role = "ADMIN";

    private String photo;

    private String imagePath;

    private char gender;

}
