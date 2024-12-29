package org.example.finalproject.admin.mappers;

import org.example.finalproject.admin.dtos.admin.AdminDetailsDto;
import org.example.finalproject.admin.dtos.admin.AdminEditingDto;
import org.example.finalproject.admin.dtos.admin.AdminListingDto;
import org.example.finalproject.admin.dtos.admin.AdminRegistrationRequestDto;
import org.example.finalproject.admin.models.admin.Admin;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Mapper(componentModel = "spring")
@Primary
public interface AdminMapper extends SimpleMapper<Admin, AdminRegistrationRequestDto> {

    List<AdminListingDto> toAdminDetailsDtoList(List<Admin> admins);

    Admin fromEditingDto(AdminEditingDto adminEditingDto);

    AdminEditingDto toEditingDto(Admin admin);

    AdminDetailsDto toAdminDetailsDto(Admin admin);

}

