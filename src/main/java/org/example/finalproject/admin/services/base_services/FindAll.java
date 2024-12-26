package org.example.finalproject.admin.services.base_services;

import org.apache.catalina.LifecycleState;

import java.util.List;

public interface FindAll<T> {
    List<T> findAll();
}
