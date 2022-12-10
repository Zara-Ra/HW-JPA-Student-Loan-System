package ir.maktab.repository;

import java.util.List;

public interface IRepository<T> {
    void save(T object);

    void update(T object);

    void delete(T object);

    List<T> getAll();
}
