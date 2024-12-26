package org.example.finalproject.admin.services;

import org.example.finalproject.admin.dtos.admin.AdminDetailsDto;
import org.example.finalproject.admin.dtos.admin.AdminEditingDto;
import org.example.finalproject.admin.dtos.admin.AdminListingDto;
import org.example.finalproject.admin.dtos.admin.AdminRegistrationRequestDto;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.services.base_services.*;

public interface AdminService extends
        FindAll<AdminListingDto>,
        FindById<AdminDetailsDto, Long>,
        Addable<AdminRegistrationRequestDto>,
        Modifiable<AdminEditingDto, Long>,
        Removable<Long> {

    void changePassword(Long adminId, String password);

    Admin login(String email, String password);

}
