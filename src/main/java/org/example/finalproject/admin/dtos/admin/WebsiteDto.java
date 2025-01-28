package org.example.finalproject.admin.dtos.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebsiteDto {
    private long id;

    @NotNull(message = "H1 is required")
    @NotBlank(message = "H1 is required")
    @Size(min = 3, max = 100, message = "H1 must be at least 3 characters long and at most 100 characters long")
    private String h1;

    @NotNull(message = "H2 is required")
    @NotBlank(message = "H2 is required")
    @Size(min = 3, max = 100, message = "H2 must be at least 3 characters long and at most 100 characters long")
    private String h2;

    @NotNull(message = "H3 is required")
    @NotBlank(message = "H3 is required")
    @Size(min = 3, max = 100, message = "H3 must be at least 3 characters long and at most 100 characters long")
    private String h3;

//    @NotNull(message = "Image is required")
//    @NotBlank(message = "Image is required")
    private String image;
}
