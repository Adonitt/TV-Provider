package org.example.finalproject.admin.repositories;

import lombok.RequiredArgsConstructor;
import org.example.finalproject.admin.models.admin.Admin;
import org.example.finalproject.admin.models.admin.Packages;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements Runnable {
    private final AdminRepository adminRepository;
    private final PackageRepository packageRepository;


    @Override
    public void run() {
        if (adminRepository.count() == 0) {
            Admin admin = new Admin(1, "Adonit", "Halili", "1252334056", LocalDate.of(2005, 2, 12), "045350345", "Kosovo", "Ali Ajeti 72", "Podujeve", 11000, "adonit.halili@smart-tv.com", "Doni1234.", 19, "Super Admin", "/assets-a/img/656cb1bb-8734-481e-b432-037578ba7ffc_une.jpg", "M");
            adminRepository.save(admin);
        }

        if (packageRepository.count() == 0) {
            List<Packages> packages = new ArrayList<>();
            packages.add(new Packages(1, "Basic", 15.99, 150, 150, "No"));
            packages.add(new Packages(2, "Premium", 24.99, 190, 200, "Yes"));
            packages.add(new Packages(3, "Sportive", 19.99, 160, 180, "Yes"));
            packageRepository.saveAll(packages);
        }
    }

}
