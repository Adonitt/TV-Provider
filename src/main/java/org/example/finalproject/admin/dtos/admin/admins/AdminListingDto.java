package org.example.finalproject.admin.dtos.admin.admins;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.finalproject.admin.models.admin.AdminRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminListingDto {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String photo;
    private AdminRole role;
}
