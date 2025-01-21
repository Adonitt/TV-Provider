package org.example.finalproject.user.repositories;

import org.example.finalproject.user.entities.ClientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<ClientsEntity, Long> {

}
