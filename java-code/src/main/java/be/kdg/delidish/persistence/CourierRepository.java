package be.kdg.delidish.persistence;

import be.kdg.delidish.business.domain.person.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CourierRepository {
    INSTANCE;
    private Map<Integer, Courier> map = new HashMap<>();

    public Courier get(int id) {
        return map.get(id);
    }

    // getAll
    public List<Courier> getAll() {
        return new ArrayList<>(map.values());
    }

    public void update(Courier courier) {
        map.put(courier.getPersonId(), courier);
    }

    /**
     * @param courier
     * @return courier
     */

    public Courier add(Courier courier) {
        // Set the id of the courier
        courier.setPersonId(map.size());
        map.put(courier.getPersonId(), courier);

        return courier;
    }
}