package org.example.finalproject.admin.services.base_services;
@FunctionalInterface

public interface FindById<T, Tid> {
    T findById(Tid id);
}
