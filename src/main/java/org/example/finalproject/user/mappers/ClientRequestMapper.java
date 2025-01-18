package org.example.finalproject.user.mappers;

import org.example.finalproject.admin.mappers.SimpleMapper;
import org.example.finalproject.user.dtos.clientsReq.ClientReqListingDto;
import org.example.finalproject.user.dtos.clientsReq.ClientRequestDto;
import org.example.finalproject.user.entities.ClientRequestEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface ClientRequestMapper extends SimpleMapper<ClientRequestEntity, ClientRequestDto> {

    List<ClientReqListingDto> toClientReqListingDtoList(List<ClientRequestEntity> clientRequestEntities);

    ClientRequestDto toClientDetailsDto(ClientRequestEntity clientRequestEntity);
}
