package com.example.lab4;

import com.example.lab4.exception.CustomEmptyStackException;
import java.util.ArrayList;
import java.util.List;

public class CustomStack<T> {
    private final List<T> data = new ArrayList<>();

    public void push(T item) { data.add(item); }

    public T pop() throws CustomEmptyStackException {
        if (data.isEmpty()) throw new CustomEmptyStackException("Стек пуст");
        return data.remove(data.size() - 1);
    }

    public boolean isEmpty() { return data.isEmpty(); }
    public int size() { return data.size(); }
}
