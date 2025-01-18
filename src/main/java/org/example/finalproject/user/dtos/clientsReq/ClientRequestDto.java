package org.example.finalproject.user.dtos.clientsReq;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.finalproject.user.entities.enums.Cities;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDto {

    private Long id;
    private Long ticketNr;
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
    private Cities city;

    @NotBlank(message = "Address is required.")
    @Size(max = 255, message = "Address must not exceed 255 characters.")
    private String address;


}
