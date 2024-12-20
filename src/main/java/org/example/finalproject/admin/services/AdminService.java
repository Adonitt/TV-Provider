package org.example.finalproject.admin.services;

import org.example.finalproject.admin.models.admin.Admin;

import java.util.List;


public interface AdminService {
    List<Admin> findAll();

    Admin save(Admin admin);

    Admin findById(long id);

    void deleteById(long id);

    Admin modify(Admin admin);


}
