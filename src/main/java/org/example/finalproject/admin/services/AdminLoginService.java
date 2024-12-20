package org.example.finalproject.admin.services;

import org.example.finalproject.admin.models.admin.Admin;

public interface AdminLoginService {
    Admin login(String email, String password);
}
