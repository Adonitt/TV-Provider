package org.example.finalproject.user.mappers;

import org.example.finalproject.admin.mappers.SimpleMapper;
import org.example.finalproject.user.dtos.AboRequestDto;
import org.example.finalproject.user.entities.ClientRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AboMapper extends SimpleMapper<ClientRequestEntity, AboRequestDto> {

//    List<AboDto> toAboDtoList(List<Client> clients);
//
//    List<Client> toClientList(List<AboDto> aboDtos);

}
