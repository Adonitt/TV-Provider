package org.example.finalproject.admin.repositories;

import org.example.finalproject.admin.models.WebsiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteRepository extends JpaRepository<WebsiteEntity, Long> {
}
