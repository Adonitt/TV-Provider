package org.example.finalproject.user.services.impls;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.user.dtos.clients.ClientDto;
import org.example.finalproject.user.dtos.clients.ClientRegistrationDto;
import org.example.finalproject.user.dtos.clients.ClientReqListingDto;
import org.example.finalproject.user.dtos.clients.ClientRequestDto;
import org.example.finalproject.user.entities.ClientsEntity;
import org.example.finalproject.user.entities.enums.StatusEnum;
import org.example.finalproject.user.mappers.ClientMapper;
import org.example.finalproject.user.repositories.ClientRequestRepository;
import org.example.finalproject.user.repositories.ClientsRepository;
import org.example.finalproject.user.services.ClientRequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        clientRequestEntity.setStatus(StatusEnum.SAVED);

        var savedEntity = clientsRepository.save(entity);

        mapper.toClientRequestDto(savedEntity);
        mapper.toClientDto(savedEntity);
        return mapper.toClientRegDto(savedEntity);
    }

    @Override
    public ClientDto findClientById(Long id) {
        var exists = clientsRepository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Client with id " + id + " not found");
        }
        return mapper.toClientDto(exists.get());
    }

    @Override
    public ClientDto createClientManually(ClientDto clientDto) {
        var entity = mapper.toClientEntity(clientDto);
        var savedEntity = clientsRepository.save(entity);
        return mapper.toClientDto(savedEntity);
    }

    @Override
    public boolean extendSubscriptionByOneMonth(Long clientId) {
        Optional<ClientsEntity> clientOpt = clientsRepository.findById(clientId);

        if (clientOpt.isPresent()) {
            ClientsEntity client = clientOpt.get();
            client.setSubscriptionEndDate(client.getSubscriptionEndDate().plusMonths(1));
            clientsRepository.save(client);
            return true;
        }

        return false;
    }

    @Override
    public boolean extendSubscriptionByOneYear(Long clientId, String modifiedBy) {
        Optional<ClientsEntity> clientOpt = clientsRepository.findById(clientId);

        if (clientOpt.isPresent()) {
            ClientsEntity client = clientOpt.get();
            client.setContractExpiryDate(client.getContractExpiryDate().plusYears(1));
            client.setModifiedBy(modifiedBy);
            clientsRepository.save(client);
            return true;
        }

        return false;
    }


    @Override
    public void modify(ClientDto dto, Long id) {
        ClientsEntity existingClient = clientsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " not found"));

        existingClient.setFirstName(dto.getFirstName());
        existingClient.setLastName(dto.getLastName());
        existingClient.setEmail(dto.getEmail());
        existingClient.setPhone(dto.getPhone());
        existingClient.setCity(dto.getCity());
        existingClient.setAddress(dto.getAddress());
        existingClient.setSubscriptionPlan(dto.getSubscriptionPlan());
        existingClient.setBillingAddress(dto.getBillingAddress());
        existingClient.setPreferredLanguage(dto.getPreferredLanguage());
        existingClient.setDeviceType(dto.getDeviceType());
        existingClient.setModifiedBy(dto.getModifiedBy());
        existingClient.setModifiedTime(dto.getModifiedTime());
        existingClient.setNotes(dto.getNotes());
        clientsRepository.save(existingClient);
    }
}
