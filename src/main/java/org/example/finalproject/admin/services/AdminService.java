package org.example.finalproject.admin.services;

import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin findById(long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public void deleteById(long id) {
        adminRepository.deleteById(id);
    }

    public Admin modify(Admin admin) {
        var existingAdmin = findById(admin.getId());
        if (existingAdmin == null) {
            return null;
        }

        existingAdmin.setName(admin.getName());
        existingAdmin.setSurname(admin.getSurname());
        existingAdmin.setDateOfBirth(admin.getDateOfBirth());
        existingAdmin.setPhoneNumber(admin.getPhoneNumber());
        existingAdmin.setGender(admin.getGender());
        existingAdmin.setCountry(admin.getCountry());
        existingAdmin.setAddress(admin.getAddress());
        existingAdmin.setPostcode(admin.getPostcode());
        existingAdmin.setCity(admin.getCity());
        existingAdmin.setPhoto(admin.getPhoto());

        return adminRepository.save(existingAdmin);
    }


}
