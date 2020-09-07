package com.internet.shop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, V> {
    T create(T element);

    Optional<T> get(V id);

    List<T> getAll();

    T update(T element);

    boolean deleteById(V id);

    boolean delete(T element);
}
