package org.example.finalproject.user.services;

import org.example.finalproject.admin.services.base_services.*;
import org.example.finalproject.user.dtos.clientsReq.ClientReqListingDto;
import org.example.finalproject.user.dtos.clientsReq.ClientRequestDto;

public interface ClientRequestService extends
        FindAll<ClientReqListingDto>,
        FindById<ClientRequestDto, Long>,
        Addable<ClientRequestDto>,
        Removable<Long> {

//    List<ClientRequestEntity> getAllClientRequests();
//
//    ClientRequestEntity getClientRequestById(Long id);
//
//    void createClientRequest(ClientRequestDto clientRequestDto);
//
//    ClientRequestEntity updateClientRequest(ClientRequestEntity clientRequestEntity);
//
//    void deleteClientRequest(Long id);
}
