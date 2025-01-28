package org.example.finalproject.admin.mappers;

import org.example.finalproject.admin.dtos.admin.WebsiteDto;
import org.example.finalproject.admin.models.WebsiteEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

@Primary
@Mapper(componentModel = "spring")
public interface WebsiteMapper extends SimpleMapper<WebsiteEntity, WebsiteDto> {
}
