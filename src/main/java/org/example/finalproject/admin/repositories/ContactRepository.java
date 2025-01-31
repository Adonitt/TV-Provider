package org.example.finalproject.admin.repositories;

import org.example.finalproject.admin.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
