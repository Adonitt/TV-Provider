package org.example.finalproject.admin.dtos.admin.packages;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.models.admin.PackageEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageRegistrationDto {

    private long id;

    @NotBlank(message = "Name is required")
    @NotNull(message = "Name is required")
    @Size(min = 4, max = 50, message = "Name must be at least 4 characters long and at most 50 characters long")
    private PackageEnum name;

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

    @NotNull(message = "Description1 is required")
    @NotBlank(message = "Description1  is required")
    @Size(min = 4, max = 500, message = "Name must be at least 4 characters long and at most 50 characters long")
    private String description1;

    @NotNull(message = "Description2 is required")
    @NotBlank(message = "Description2 is required")
    @Size(min = 4, max = 500, message = "Name must be at least 4 characters long and at most 50 characters long")
    private String description2;

    private String photo;

    @NotNull(message = "Channels included is required")
    @NotBlank(message = "Channels included is required")
    @Size(min = 4, max = 200, message = "Name must be at least 4 characters long and at most 50 characters long")
    private String channelsIncluded;

    @NotNull(message = "Affordable pricing is required")
    @NotBlank(message = "Affordable pricing is required")
    @Size(min = 4, max = 200, message = "Name must be at least 4 characters long and at most 50 characters long")
    private String affordablePricing;

    @NotNull(message = "HD streaming is required")
    @NotBlank(message = "HD streaming is required")
    @Size(min = 4, max = 200, message = "Name must be at least 4 characters long and at most 50 characters long")
    private String hdStreaming;

    @NotNull(message = "User friendly features is required")
    @NotBlank(message = "User friendly features is required")
    @Size(min = 4, max = 200, message = "Name must be at least 4 characters long and at most 50 characters long")
    private String userFriendlyFeatures;

}
