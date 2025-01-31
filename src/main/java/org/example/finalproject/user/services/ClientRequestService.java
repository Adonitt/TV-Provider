package org.example.finalproject.user.services;

import org.example.finalproject.admin.services.base_services.*;
import org.example.finalproject.user.dtos.clients.ClientDto;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.dtos.clients.ClientReqListingDto;
import org.example.finalproject.user.dtos.clients.ClientRequestDto;

public interface ClientRequestService extends
        FindAll<ClientReqListingDto>,
        FindById<ClientRequestDto, Long>,
        Addable<ClientRequestDto>,
        Removable<Long>, Modifiable<ClientDto, Long> {

    ClientRegistrationDto saveClient(ClientRegistrationDto dto);

    ClientDto findClientById(Long id);

    ClientDto createClientManually(ClientDto clientDto);

    boolean extendSubscriptionByOneMonth(Long clientId);

    boolean extendSubscriptionByOneYear(Long clientId, String modifiedBy);

}
