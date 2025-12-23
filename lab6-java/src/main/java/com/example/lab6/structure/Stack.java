package com.example.lab6.structure;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> {
    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public void push(T element) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = element;
    }

    public T pop() {
        if (size == 0) throw new EmptyStackException();
        T element = data[--size];
        data[size] = null;
        return element;
    }

    public T peek() {
        if (size == 0) throw new EmptyStackException();
        return data[size - 1];
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }
}
