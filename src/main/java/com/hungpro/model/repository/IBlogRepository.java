package com.hungpro.model.repository;


import java.util.List;

public interface IBlogRepository<T, E> {
    List<T> findAll();

    T findByID(E id);

    void save(T p);

    void delete(E id);
}
