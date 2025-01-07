package org.example.finalproject.admin.services.base_services;

import java.util.List;
@FunctionalInterface
public interface FindAll<T> {
    List<T> findAll();
}
