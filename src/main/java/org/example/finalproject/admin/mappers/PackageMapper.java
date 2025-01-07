package org.example.finalproject.admin.mappers;

import org.example.finalproject.admin.dtos.admin.packages.PackageRegistrationDto;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = "spring")
public interface PackageMapper extends SimpleMapper<PackageEntity, PackageRegistrationDto> {
    PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);



}
