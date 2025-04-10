package org.example.finalproject.admin.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.dtos.admin.admins.AdminDetailsDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminEditingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminListingDto;
import org.example.finalproject.admin.dtos.admin.admins.AdminRegistrationRequestDto;

import org.example.finalproject.admin.mappers.AdminMapper;
import org.example.finalproject.admin.models.admin.AdminEntity;
import org.example.finalproject.admin.repositories.AdminRepository;
import org.example.finalproject.admin.services.interfaces.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {
    private final AdminRepository repository;
    private final AdminMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminRegistrationRequestDto add(AdminRegistrationRequestDto dto) {
        var entity = mapper.toEntity(dto);

        String encryptedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(encryptedPassword);

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
        AdminEntity existingAdmin = repository.findById(adminId).orElseThrow(() -> new EntityNotFoundException("Admin with ID " + adminId + " not found"));
        if (existingAdmin != null) {
            existingAdmin.setPassword(passwordEncoder.encode(password));
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
        AdminEntity existingAdmin = repository.findById(adminId)
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
    public AdminEntity login(String username, String password) {
        var user = repository.findByEmail(username);
        if (user.isEmpty()) {
            System.out.println("User not found");
            return null;
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            System.out.println("Password is incorrect");
            return null;
        }
        return user.get();
    }
}
