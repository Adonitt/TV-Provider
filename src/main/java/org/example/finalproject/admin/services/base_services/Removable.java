package org.example.finalproject.admin.services.base_services;

@FunctionalInterface
public interface Removable<Tid> {
    void removeById(Tid id);
}
