package com.kpi.project.second.service.dao;

/**
 * Generic Dao interface for all entities.
 *
 * @param <T> generic parameter for entity
 */
public interface Dao<T> {

    T findById(int id);

    T insert(T model);

    T update(T model);

    T delete(T model);
}
