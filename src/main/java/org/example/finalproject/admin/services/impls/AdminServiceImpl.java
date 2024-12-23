package org.example.finalproject.admin.services.impls;

import org.example.finalproject.admin.mappers.AdminMapper;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.repositories.AdminRepository;
import org.example.finalproject.admin.services.AdminService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper mapper;

    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper mapper) {
        this.adminRepository = adminRepository;
        this.mapper = mapper;
        if (adminRepository.count() == 0) {
            adminRepository.save(new Admin(1, "Adonit", "Halili", "1252334056", LocalDate.of(2005, 2, 12), "045350345", "Kosovo", "Ali Ajeti 72", "Podujeve", 11000, "adonit.halili@smart-tv.com", "Doni1234.", 19, "Super Admin", "/assets-a/img/449e24ee-599f-412f-8da7-1c4c26e756ea_une2.jpg", null, "M"));
        }
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


    @Override
    public void changePassword(Long adminId, String password) {
        Admin existingAdmin = adminRepository.findById(adminId).orElse(null);
        if (existingAdmin != null) {
            existingAdmin.setPassword(password);
        }
        adminRepository.save(existingAdmin);
    }

}
