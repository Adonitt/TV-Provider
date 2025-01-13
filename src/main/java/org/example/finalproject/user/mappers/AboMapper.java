package org.example.finalproject.user.mappers;

import org.example.finalproject.admin.mappers.SimpleMapper;
import org.example.finalproject.user.dtos.AboDto;
import org.example.finalproject.user.models.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AboMapper extends SimpleMapper<Client, AboDto> {

//    List<AboDto> toAboDtoList(List<Client> clients);
//
//    List<Client> toClientList(List<AboDto> aboDtos);

}
