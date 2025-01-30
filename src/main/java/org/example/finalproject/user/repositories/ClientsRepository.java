package org.example.finalproject.user.repositories;

import org.example.finalproject.user.entities.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {
//    Optional<ClientsEntity> findClientWithPackageId(Long id);

    boolean existsByEmail(String email);

    Optional<ClientsEntity> findByClientNr(Long clientNr);
}