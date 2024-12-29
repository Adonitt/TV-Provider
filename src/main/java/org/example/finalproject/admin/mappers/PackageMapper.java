package org.example.finalproject.admin.mappers;

import org.example.finalproject.admin.dtos.admin.AdminListingDto;
import org.example.finalproject.admin.dtos.admin.packages.PackageRegistrationDto;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.models.admin.Packages;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface PackageMapper extends SimpleMapper<Packages, PackageRegistrationDto> {
    PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);



}
