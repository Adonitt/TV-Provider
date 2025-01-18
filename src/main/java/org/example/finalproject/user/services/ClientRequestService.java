package org.example.finalproject.user.services;

import org.example.finalproject.user.models.ClientRequestEntity;

import java.util.List;

public interface ClientRequestService {
    List<ClientRequestEntity> getAllClientRequests();
    ClientRequestEntity getClientRequestById(Long id);
    ClientRequestEntity createClientRequest(ClientRequestEntity clientRequestEntity);
    ClientRequestEntity updateClientRequest(ClientRequestEntity clientRequestEntity);
    void deleteClientRequest(Long id);
}
