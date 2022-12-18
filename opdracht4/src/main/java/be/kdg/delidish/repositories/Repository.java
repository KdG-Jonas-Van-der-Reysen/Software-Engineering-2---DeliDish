package be.kdg.delidish.repositories;

public interface Repository<K,V> {

    boolean update(K key,V value);

    V insert(K key, V value);

    V findById(K id);

    long count();

}
