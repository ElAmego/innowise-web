package com.egoramel.cafe.model.dao;

import com.egoramel.cafe.exception.CustomException;

import java.util.List;

public interface Dao<E> {
    List<E> findAll();
    boolean save(final E entity) throws CustomException;
    void delete(final E entity);
    E update(final E entity);
}