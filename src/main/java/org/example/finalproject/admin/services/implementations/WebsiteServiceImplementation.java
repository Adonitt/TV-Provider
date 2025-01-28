package org.example.finalproject.admin.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.WebsiteDto;
import org.example.finalproject.admin.mappers.WebsiteMapper;
import org.example.finalproject.admin.models.WebsiteEntity;
import org.example.finalproject.admin.repositories.WebsiteRepository;
import org.example.finalproject.admin.services.interfaces.WebsiteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebsiteServiceImplementation implements WebsiteService {
    private final WebsiteRepository repository;
    private final WebsiteMapper mapper;

    @Override
    public WebsiteDto add(WebsiteDto dto) {
        var entity = mapper.toEntity(dto);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }


    @Override
    public WebsiteDto findById(Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Website with id " + id + " not found");
        }
        return mapper.toDto(exists.get());
    }

    @Override
    public void modify(WebsiteDto dto, Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Website with id " + id + " not found");
        }
        var entity1 = mapper.toEntity(dto);
        repository.save(entity1);
    }

    @Override
    public WebsiteEntity fetchData() {
        return repository.findAll().stream().findFirst().orElse(null);
    }

}
