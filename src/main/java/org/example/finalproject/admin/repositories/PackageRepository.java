package org.example.finalproject.admin.repositories;

import org.example.finalproject.admin.models.admin.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Long> {

}
