package be.kdg.delidish.repositories;

public interface Repository<V> {

    boolean update(int key,V value);

    V insert(int key, V value);

    V findById(int id);

    long count();

}
