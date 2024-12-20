package org.example.finalproject.admin.mappers;

import org.example.finalproject.admin.dtos.admin.AdminDto;
import org.example.finalproject.admin.dtos.admin.AdminRegistrationRequestDto;
import org.example.finalproject.admin.infrastructure.mapping.SimpleMapper;
import org.example.finalproject.admin.models.admin.Admin;
import org.springframework.stereotype.Component;

public interface AdminMapper extends SimpleMapper<Admin, AdminDto> {
    Admin fromAdminRegistrationDto(AdminRegistrationRequestDto adminRegistrationRequestDto);
}
