package org.example.finalproject.admin.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.channels.ChannelsDto;
import org.example.finalproject.admin.mappers.ChannelsMapper;
import org.example.finalproject.admin.repositories.ChannelRepository;
import org.example.finalproject.admin.services.interfaces.ChannelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelsServiceImplementation implements ChannelService {
    private final ChannelRepository repository;
    private final ChannelsMapper mapper;

    @Override
    public ChannelsDto add(ChannelsDto dto) {
        var entity = mapper.toEntity(dto);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public List<ChannelsDto> findAll() {
        var channelsList = repository.findAll();
        return mapper.toChannelsDtoList(channelsList);
    }

    @Override
    public void modify(ChannelsDto dto, Integer id) {
        var existingChannel = repository.findById(id);
        if (existingChannel.isEmpty()) {
            throw new EntityNotFoundException("Channel with id " + id + " not found");
        }
        var entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Override
    public void removeById(Integer id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Channel with id " + id + " not found");
        }
        repository.deleteById(id);

    }
}
