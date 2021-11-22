package repository;

import java.util.List;

public interface Repository<T, T2> {
    T get(Integer id);
    List<T> getAll();
    void add(T t);
    void addTweet(T t, String message);
    List<T2> getAllTweets();
    void removeTweet(T2 t2);
    void update(T t);
    void remove(T t);
}
