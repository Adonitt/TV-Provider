package org.example.finalproject.admin.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.packages.PackageRegistrationDto;
import org.example.finalproject.admin.mappers.PackageMapper;
import org.example.finalproject.admin.models.admin.PackageEntity;
import org.example.finalproject.admin.repositories.PackageRepository;
import org.example.finalproject.admin.services.interfaces.PackageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageServiceImplementation implements PackageService {
    private final PackageRepository repository;
    private final PackageMapper mapper;


    @Override
    public PackageRegistrationDto add(PackageRegistrationDto dto) {
        var entity = mapper.toEntity(dto);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public List<PackageEntity> findAll() {
        var packagesList = repository.findAll();
        return packagesList;
    }

    @Override
    public PackageEntity findById(Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Package with id " + id + " not found");
        }
        return exists.get();
    }

    @Override
    public void modify(PackageRegistrationDto dto, Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Package with id " + id + " not found");
        }
        var entity = mapper.toEntity(dto);
        repository.save(entity);
    }

    @Override
    public void removeById(Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Package with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
