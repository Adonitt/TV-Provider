package org.example.finalproject.user.repositories;

import org.example.finalproject.user.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


}
