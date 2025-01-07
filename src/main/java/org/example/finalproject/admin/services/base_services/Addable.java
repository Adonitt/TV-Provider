package org.example.finalproject.admin.services.base_services;

@FunctionalInterface
public interface Addable<T> {
    T add(T entity);
}
