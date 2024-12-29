package org.example.finalproject.admin.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.AdminDetailsDto;
import org.example.finalproject.admin.dtos.admin.AdminEditingDto;
import org.example.finalproject.admin.dtos.admin.AdminListingDto;
import org.example.finalproject.admin.dtos.admin.AdminRegistrationRequestDto;

import org.example.finalproject.admin.mappers.AdminMapper;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.repositories.AdminRepository;
import org.example.finalproject.admin.services.interfaces.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final AdminRepository repository;
    private final AdminMapper mapper;


    @Override
    public AdminRegistrationRequestDto add(AdminRegistrationRequestDto dto) {
        var entity = mapper.toEntity(dto);
        var savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }


    @Override
    public void removeById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Admin with id " + id + " not found");
        }
        repository.deleteById(id);
    }


    @Override
    public AdminDetailsDto findById(Long id) {
        var exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new EntityNotFoundException("Admin with id " + id + " not found");
        }
        return mapper.toAdminDetailsDto(exists.get());
    }

    @Override
    public void changePassword(Long adminId, String password) {
        Admin existingAdmin = repository.findById(adminId).orElse(null);
        if (existingAdmin != null) {
            existingAdmin.setPassword(password);
        }
        repository.save(existingAdmin);
    }

    @Override
    public List<AdminListingDto> findAll() {
        var adminsList = repository.findAll();
        return mapper.toAdminDetailsDtoList(adminsList);
    }

    @Override
    public void modify(AdminEditingDto adminEditingDto, Long adminId) {
        Admin existingAdmin = repository.findById(adminId)
                .orElseThrow(() -> new EntityNotFoundException("Admin with ID " + adminId + " not found"));

        // Update the fields of the admin
        existingAdmin.setName(adminEditingDto.getName());
        existingAdmin.setSurname(adminEditingDto.getSurname());
        existingAdmin.setPersonalNumber(adminEditingDto.getPersonalNumber());
        existingAdmin.setDateOfBirth(adminEditingDto.getDateOfBirth());
        existingAdmin.setPhoneNumber(adminEditingDto.getPhoneNumber());
        existingAdmin.setCountry(adminEditingDto.getCountry());
        existingAdmin.setAddress(adminEditingDto.getAddress());
        existingAdmin.setCity(adminEditingDto.getCity());
        existingAdmin.setPostcode(adminEditingDto.getPostcode());
        existingAdmin.setRole(adminEditingDto.getRole());
        existingAdmin.setGender(adminEditingDto.getGender());

        if (adminEditingDto.getPhoto() != null && !adminEditingDto.getPhoto().isEmpty()) {
            existingAdmin.setPhoto(adminEditingDto.getPhoto());
        }

        repository.save(existingAdmin);
    }

    @Override
    public Admin login(String username, String password) {
        var user = repository.findByEmail(username);
        if (user.isEmpty()) {
            System.out.println("User not found");
            return null;
        }
        if (!user.get().getPassword().equals(password)) {
            System.out.println("Password is incorrect");
            return null;
        }
        return user.get();
    }
}
