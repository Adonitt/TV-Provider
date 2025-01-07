package org.example.finalproject.admin.repositories;

import org.example.finalproject.admin.models.admin.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByEmail(String email);

    AdminEntity findByName(String name);

    AdminEntity findBySurname(String surname);

    List<AdminEntity> findByNameContainingIgnoreCase(String name);

    List<AdminEntity> findBySurnameContainingIgnoreCase(String surname);

    List<AdminEntity> findByAddressContainingIgnoreCase(String address);

}
