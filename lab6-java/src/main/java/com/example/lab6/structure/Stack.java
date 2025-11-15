package com.example.lab6.structure;

import java.util.Arrays;
import java.util.EmptyStackException;

// Задание 2: Обобщенный стек
public class Stack<T> {
    private T[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public void push(T element) {
        if (size == data.length) {
            // Увеличиваем массив в 2 раза
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = element;
    }

    public T pop() {
        if (size == 0) throw new EmptyStackException();
        T element = data[--size];
        data[size] = null; // предотвращаем утечку памяти
        return element;
    }

    public T peek() {
        if (size == 0) throw new EmptyStackException();
        return data[size - 1];
    }
    
    public int size() { return size; }
}