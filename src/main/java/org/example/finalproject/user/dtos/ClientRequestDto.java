package org.example.finalproject.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.finalproject.user.models.enums.Cities;
import org.example.finalproject.user.models.enums.Offers;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {

    private Long id;

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name must not exceed 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 50, message = "Last name must not exceed 50 characters.")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotBlank(message = "Phone number is required.")
    @Pattern(
            regexp = "^\\+?[0-9\\-\\s]{7,15}$",
            message = "Invalid phone number. Must be between 7 and 15 digits, and may include '+' or '-' symbols."
    )
    private String phone;

    @NotNull(message = "City is required.")
    @NotBlank(message = "City is required.")
    private Cities city;

    @NotBlank(message = "Offer is required.")
    @NotNull(message = "Offer is required.")
    private Offers offers;

    @NotBlank(message = "Address is required.")
    @Size(max = 255, message = "Address must not exceed 255 characters.")
    private String address;



}
