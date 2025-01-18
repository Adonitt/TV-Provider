package org.example.finalproject.user.mappers.impls;

import org.example.finalproject.user.dtos.ClientRequestDto;
import org.example.finalproject.user.mappers.ClientRequestMapper;
import org.example.finalproject.user.models.ClientRequestEntity;

public class ClientRequestMapperImpl implements ClientRequestMapper {
    @Override
    public ClientRequestEntity toEntity(ClientRequestDto clientRequestDto) {
        ClientRequestEntity client = new ClientRequestEntity();
        client.setId(clientRequestDto.getId());
        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setAddress(clientRequestDto.getAddress());
        client.setEmail(clientRequestDto.getEmail());
        client.setPhone(clientRequestDto.getPhone());
        client.setCity(clientRequestDto.getCity());
        client.setOffer(clientRequestDto.getOffers());
        return client;
    }

    @Override
    public ClientRequestDto toDto(ClientRequestEntity clientRequestEntity) {
        ClientRequestDto clientDto = new ClientRequestDto();
        clientDto.setId(clientRequestEntity.getId());
        clientDto.setFirstName(clientRequestEntity.getFirstName());
        clientDto.setLastName(clientRequestEntity.getLastName());
        clientDto.setAddress(clientRequestEntity.getAddress());
        clientDto.setEmail(clientRequestEntity.getEmail());
        clientDto.setPhone(clientRequestEntity.getPhone());
        clientDto.setCity(clientRequestEntity.getCity());
        clientDto.setOffers(clientRequestEntity.getOffer());
        return clientDto;
    }
}
