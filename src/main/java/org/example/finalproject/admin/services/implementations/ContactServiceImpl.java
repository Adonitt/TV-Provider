package org.example.finalproject.admin.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.admins.AdminDetailsDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminEditingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminListingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminRegistrationRequestDto;
import org.example.finalproject.admin.models.Contact;
import org.example.finalproject.admin.repositories.ContactRepository;
import org.example.finalproject.admin.services.interfaces.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    @Override
    public Contact add(Contact entity) {
        return repository.save(entity);
    }

    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Contact with id " + id + " not found");
        }
        return exists.get();
    }

    @Override
    public void modify(Contact entity, Long id) {

    }

    @Override
    public void removeById(Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Contact with id " + id + " not found");
        }
        repository.deleteById(id);

    }
}
