package com.internet.shop.service;

import java.util.List;

public interface GenericService<T, V> {
    T create(T element);

    T get(V id);

    List<T> getAll();

    T update(T element);

    boolean deleteById(V id);

    boolean delete(T element);
}
