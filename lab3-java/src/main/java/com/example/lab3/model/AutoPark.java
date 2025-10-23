package com.example.lab3.model;

import java.util.HashMap;
import java.util.Map;

public class AutoPark {
    /**
     * Ключ: госномер (строка), Значение: CarRecord.
     */
    private final Map<String, CarRecord> registry = new HashMap<>();

    public void upsert(String plate, CarRecord car) {
        registry.put(plate, car);
    }

    public CarRecord find(String plate) {
        return registry.get(plate);
    }

    public CarRecord remove(String plate) {
        return registry.remove(plate);
    }

    public int size() { return registry.size(); }
    public boolean isEmpty() { return registry.isEmpty(); }

    @Override
    public String toString() { return registry.toString(); }
}
