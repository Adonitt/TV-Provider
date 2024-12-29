package org.example.finalproject.admin.dtos.admin.packages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageRegistrationDto {

    private long id;

    @NotBlank(message = "Name is required")
    @NotNull(message = "Name is required")
    @Size(min = 4, max = 50, message = "Name must be at least 4 characters long and at most 50 characters long")
    private String name;

    @Positive(message = "Price must be a positive number")
    @NotNull(message = "Price is required")
    private double price;

    @NotNull(message = "MBPS is required")
    private int mbps;

    @NotNull(message = "Number of channels is required")
    @Positive(message = "Number of channels must be a positive number")
    private int nrOfChannels;

    @NotNull(message = "Sports channels is required")
    @NotBlank(message = "Sports channels is required")
    private String sportsChannels; // yes or no

}
