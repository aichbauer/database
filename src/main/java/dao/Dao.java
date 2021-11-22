package dao;

import java.util.List;

public interface Dao<T> {
    void create(T t);
    T read(Integer id);
    List<T> read();
    List<T> readAll(Integer T);
    void update(T t);
    void delete(T t);
}
