package org.example.finalproject.user.repositories;

import org.example.finalproject.user.models.ClientRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRequestRepository extends JpaRepository<ClientRequestEntity, Long> {
Long countAllByEmailAndPhone(String email, String phone);
}
