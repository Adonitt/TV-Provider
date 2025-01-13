package org.example.finalproject.admin.dtos.admin.admins;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminListingDto {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String photo;
    private String role;
}
