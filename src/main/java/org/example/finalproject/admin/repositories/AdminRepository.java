package org.example.finalproject.admin.repositories;

import org.example.finalproject.admin.models.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);

    Admin findByName(String name);

    Admin findBySurname(String surname);

    List<Admin> findByNameContainingIgnoreCase(String name);

    List<Admin> findBySurnameContainingIgnoreCase(String surname);

    List<Admin> findByAddressContainingIgnoreCase(String address);
}
