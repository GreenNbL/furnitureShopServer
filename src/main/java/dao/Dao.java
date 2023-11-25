package dao;

import java.util.List;

public interface Dao<T> {
    void save(T obj);
    void delete(T obj);
    void update(T obj);
    T findById(int id);
    List<T> findAll();
}
