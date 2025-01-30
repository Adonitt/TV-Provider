package org.example.finalproject.user.mappers;

import org.example.finalproject.admin.mappers.SimpleMapper;
import org.example.finalproject.user.dtos.clients.ClientDto;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.dtos.clients.ClientReqListingDto;
import org.example.finalproject.user.dtos.clients.ClientRequestDto;
import org.example.finalproject.user.dtos.sub.InvoiceDto;
import org.example.finalproject.user.dtos.sub.SubDetailsDto;
import org.example.finalproject.user.dtos.sub.SubDto;
import org.example.finalproject.user.entities.ClientRequestEntity;
import org.example.finalproject.user.entities.ClientsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Mapper(componentModel = "spring")
public interface ClientMapper extends SimpleMapper<ClientRequestEntity, ClientRequestDto> {

    // Existing mappings
    List<ClientReqListingDto> toClientReqListingDtoList(List<ClientRequestEntity> clientRequestEntities);
    ClientRequestDto toClientDetailsDto(ClientRequestEntity clientRequestEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "clientRegistrationDto.status")
    @Mapping(target = "declinedBy", source = "clientRegistrationDto.declinedBy")
    ClientDto mergeDtosToClientDto(ClientRequestDto clientRequestDto, ClientRegistrationDto clientRegistrationDto);

    ClientsEntity toClientEntity(ClientDto clientDto);
    ClientRegistrationDto toClientRegDto(ClientsEntity clientsEntity);
    ClientRequestDto toClientRequestDto(ClientsEntity clientsEntity);
    ClientDto toClientDto(ClientsEntity clientsEntity);

    SubDto toSubDto(ClientsEntity clientsEntity);

    SubDetailsDto toSubDetailsDto(ClientsEntity clientsEntity);

    InvoiceDto toInvoiceDto(ClientsEntity clientsEntity);

}