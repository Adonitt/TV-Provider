package org.example.finalproject.user.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.user.models.ClientRequestEntity;
import org.example.finalproject.user.repositories.ClientRequestRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientRequestServiceImpl implements ClientRequestService {

    private final ClientRequestRepository repository;

    @Override
    public List<ClientRequestEntity> getAllClientRequests() {
        return repository.findAll();
    }

    @Override
    public ClientRequestEntity getClientRequestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ClientRequestEntity createClientRequest(ClientRequestEntity clientRequestEntity) {
        var existingClient = repository.findById(clientRequestEntity.getId());
        if (existingClient.isPresent()) {
            System.out.println("Client with this id exists");
            return null;
        }

        long totalClients = repository.countAllByEmailAndPhone(clientRequestEntity.getEmail(), clientRequestEntity.getPhone());
        if (totalClients > 0) {
            System.out.println("Client with this email and phone already exists");
            return null;
        }

        return repository.save(clientRequestEntity);
    }

    @Override
    public ClientRequestEntity updateClientRequest(ClientRequestEntity clientRequestEntity) {

        var existingClient = repository.findById(clientRequestEntity.getId());
        if (existingClient.isEmpty()) {
            System.out.println("Client with this id does not exist");
            return null;
        }
        return repository.save(clientRequestEntity);
    }

    @Override
    public void deleteClientRequest(Long id) {

        var existingClient = repository.findById(id);
        if (existingClient.isEmpty()) {
            System.out.println("Client with this id does not exist");
            return;
        }
        repository.deleteById(id);

    }
}
