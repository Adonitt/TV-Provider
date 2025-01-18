package org.example.finalproject.user.services.impls;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.user.dtos.clientsReq.ClientReqListingDto;
import org.example.finalproject.user.dtos.clientsReq.ClientRequestDto;
import org.example.finalproject.user.mappers.ClientRequestMapper;
import org.example.finalproject.user.repositories.ClientRequestRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientRequestServiceImpl implements ClientRequestService {
    private final ClientRequestRepository repository;
    private final ClientRequestMapper mapper;

    @Override
    public ClientRequestDto add(ClientRequestDto dto) {
        var entity = mapper.toEntity(dto);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public List<ClientReqListingDto> findAll() {
        var clientRequests = repository.findAll();
        return mapper.toClientReqListingDtoList(clientRequests);
    }

    @Override
    public ClientRequestDto findById(Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Client request with id " + id + " not found");
        }

        return mapper.toClientDetailsDto(exists.get());
    }


    @Override
    public void removeById(Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Client request with id " + id + " not found");
        }
        repository.deleteById(id);
    }

}
