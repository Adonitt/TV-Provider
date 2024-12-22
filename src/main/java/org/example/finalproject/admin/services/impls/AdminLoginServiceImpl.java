package org.example.finalproject.admin.services.impls;

import org.example.finalproject.admin.dtos.admin.AdminRegistrationRequestDto;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.repositories.AdminRepository;
import org.example.finalproject.admin.services.AdminLoginService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    private final AdminRepository repository;

    public AdminLoginServiceImpl(AdminRepository repository) {
        this.repository = repository;
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
