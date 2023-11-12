package be.tsapasmi.factorymanagement.bl.interfaces;

import java.util.List;

public interface BaseService<T,K> {
    void create(T entity);
    List<T> getAll();
    T getOne(K id);
    T update(K id, T entity);
    void delete(K id);
}
