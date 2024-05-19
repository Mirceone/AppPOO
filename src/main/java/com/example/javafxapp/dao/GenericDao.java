package com.example.javafxapp.dao;

import jakarta.persistence.EntityManager;

import java.util.List;

public interface GenericDao<T> {

    void save(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(T entity);

}
