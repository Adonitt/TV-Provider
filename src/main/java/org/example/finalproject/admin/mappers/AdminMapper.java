package org.example.finalproject.admin.mappers;

import org.example.finalproject.admin.dtos.admin.admins.AdminDetailsDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminEditingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminListingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminRegistrationRequestDto;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Mapper(componentModel = "spring")
@Primary
public interface AdminMapper extends SimpleMapper<AdminEntity, AdminRegistrationRequestDto> {

    List<AdminListingDto> toAdminDetailsDtoList(List<AdminEntity> admins);

    AdminEntity fromEditingDto(AdminEditingDto adminEditingDto);

    AdminEditingDto toEditingDto(AdminEntity admin);

    AdminDetailsDto toAdminDetailsDto(AdminEntity admin);

}

