package be.kdg.delidish.repositories.memory;

import be.kdg.delidish.repositories.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemoryRepository<K, V> implements Repository<K, V> {
    private static Logger log = Logger.getLogger("demo.persistence.memory");
    protected Map<K, V> data = new ConcurrentHashMap<>();

    public MemoryRepository() {
        log.info("new memory repository " + getClass());
    }

    @Override
    public boolean update(K key, V value) {
        return data.put(key, value)!=null;
    }

    @Override
    public V insert(K key, V value) {
        update(key,value);
        return value;
    }

    @Override
    public V findById(K id) {
        return data.get(id);
    }

    @Override
    public long count() {
        return data.size();
    }

    public int getNextAvailableId() {
        // Check if data.size() is available
        if(data.get(data.size()) != null) {
            return getNextAvailableId();
        } else {
            return data.size();
        }
    }

    public List<V> getAll() {
        return data.values().stream().collect(Collectors.toList());
    }


    public List<V> findWhere(Predicate<V> predicate, Comparator<V> sorter) {
        Stream<V> result = findStream(predicate);
        if (sorter != null) {
            result = result.sorted(sorter);
        }
        return result.collect(Collectors.toList());
    }

    public V findOneWhere(Predicate<V> predicate) {
        return findStream(predicate).findAny
                ().orElse(null);
    }

    private Stream<V> findStream(Predicate<V> predicate) {
        return data.values().stream().filter(predicate);
    }

    public void clear() {
        data.clear();
    }
}
