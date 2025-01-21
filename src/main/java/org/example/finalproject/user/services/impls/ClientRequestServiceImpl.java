package org.example.finalproject.user.services.impls;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.dtos.clients.ClientReqListingDto;
import org.example.finalproject.user.dtos.clients.ClientRequestDto;
import org.example.finalproject.user.mappers.ClientMapper;
import org.example.finalproject.user.repositories.ClientRequestRepository;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientRequestServiceImpl implements ClientRequestService {
    private final ClientRequestRepository clientRequestRepository;
    private final ClientMapper mapper;
    private final ClientsRepository clientsRepository;

    @Override
    public ClientRequestDto add(ClientRequestDto dto) {
        var entity = mapper.toEntity(dto);
        var savedEntity = clientRequestRepository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public List<ClientReqListingDto> findAll() {
        var clientRequests = clientRequestRepository.findAll();
        return mapper.toClientReqListingDtoList(clientRequests);
    }

    @Override
    public ClientRequestDto findById(Long id) {
        var exists = clientRequestRepository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Client request with id " + id + " not found");
        }
        return mapper.toClientDetailsDto(exists.get());
    }


    @Override
    public void removeById(Long id) {
        var exists = clientRequestRepository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Client request with id " + id + " not found");
        }
        clientRequestRepository.deleteById(id);
    }

    @Override
    public ClientRegistrationDto saveClient(ClientRegistrationDto dto) {

        var clientRequestEntity = clientRequestRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client request with ID " + dto.getId() + " not found"));

        var clientRequestDto = mapper.toDto(clientRequestEntity);

        var mergedDto = mapper.mergeDtosToClientDto(clientRequestDto, dto);

        var entity = mapper.toClientEntity(mergedDto);
        var savedEntity = clientsRepository.save(entity);
        return mapper.toClientReqDto(savedEntity);
    }


}
