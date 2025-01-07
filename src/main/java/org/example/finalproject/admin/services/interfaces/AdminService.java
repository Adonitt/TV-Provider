package org.example.finalproject.admin.services.interfaces;

import org.example.finalproject.admin.dtos.admin.admins.AdminDetailsDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminEditingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminListingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminRegistrationRequestDto;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.example.finalproject.admin.services.base_services.*;

public interface AdminService extends
        FindAll<AdminListingDto>,
        FindById<AdminDetailsDto, Long>,
        Addable<AdminRegistrationRequestDto>,
        Modifiable<AdminEditingDto, Long>,
        Removable<Long> {

    void changePassword(Long adminId, String password);

    AdminEntity login(String email, String password);

}
