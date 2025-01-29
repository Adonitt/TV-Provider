package org.example.finalproject.user.repositories;

import org.example.finalproject.user.entities.ClientRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRequestRepository extends JpaRepository<ClientRequestEntity, Long> {
    Long countAllByEmailAndPhone(String email, String phone);

    Optional<ClientRequestEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
