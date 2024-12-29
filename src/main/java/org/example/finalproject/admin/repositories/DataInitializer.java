package org.example.finalproject.admin.repositories;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.Admin;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements Runnable {
    private final AdminRepository repository;


    @Override
    public void run() {
        if (repository.count() == 0) {
            Admin admin = new Admin(1, "Adonit", "Halili", "1252334056", LocalDate.of(2005, 2, 12), "045350345", "Kosovo", "Ali Ajeti 72", "Podujeve", 11000, "adonit.halili@smart-tv.com", "Doni1234.", 19, "Super Admin", "/assets-a/img/656cb1bb-8734-481e-b432-037578ba7ffc_une.jpg", "M");
            repository.save(admin);
        }
    }

}
