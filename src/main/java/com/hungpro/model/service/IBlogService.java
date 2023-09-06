package com.hungpro.model.service;

import java.util.List;

public interface IBlogService<T, E> {
    List<T> findAll();

    T findByID(E id);

    void save(T p);

    void delete(E id);
}