package com.example.lab3.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Простейшая хэш-таблица на цепочках.
 * Поддерживает null-значения, но не null-ключи (упрощение).
 */
public class HashTable<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private List<Entry<K, V>>[] table;
    private int size;
    private int threshold;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        int cap = Math.max(1, capacity);
        table = (List<Entry<K, V>>[]) new List[cap];
        threshold = (int) (cap * DEFAULT_LOAD_FACTOR);
    }

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void put(K key, V value) {
        Objects.requireNonNull(key, "key must not be null");
        ensureCapacity();

        int index = indexFor(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> e : table[index]) {
            if (e.key.equals(key)) {
                e.value = value;
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        Objects.requireNonNull(key, "key must not be null");
        int index = indexFor(key);
        List<Entry<K, V>> bucket = table[index];
        if (bucket == null) return null;
        for (Entry<K, V> e : bucket) {
            if (e.key.equals(key)) return e.value;
        }
        return null;
    }

    public V remove(K key) {
        Objects.requireNonNull(key, "key must not be null");
        int index = indexFor(key);
        List<Entry<K, V>> bucket = table[index];
        if (bucket == null) return null;
        var it = bucket.iterator();
        while (it.hasNext()) {
            Entry<K, V> e = it.next();
            if (e.key.equals(key)) {
                it.remove();
                size--;
                return e.value;
            }
        }
        return null;
    }

    private int indexFor(K key) {
        int h = key.hashCode();
        h ^= (h >>> 16);
        return (h & 0x7fffffff) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (size < threshold) return;
        List<Entry<K, V>>[] old = table;
        table = (List<Entry<K, V>>[]) new List[old.length * 2];
        threshold = (int) (table.length * DEFAULT_LOAD_FACTOR);
        size = 0;

        for (List<Entry<K, V>> bucket : old) {
            if (bucket == null) continue;
            for (Entry<K, V> e : bucket) {
                put(e.key, e.value);
            }
        }
    }

    public static final class Entry<K, V> {
        final K key;
        V value;

        Entry(K key, V value) {
            this.key = Objects.requireNonNull(key);
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }

        @Override public String toString() { return key + "=" + value; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry<?, ?> e)) return false;
            return key.equals(e.key) && Objects.equals(value, e.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }
}
