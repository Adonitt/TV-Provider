package org.example.finalproject.admin.services.base_services;

public interface Modifiable<T, Tid> {
    void modify(T entity, Tid id);

}
