package org.example.finalproject.admin.services.base_services;

public interface FindById<T, Tid> {
    T findById(Tid id);
}
