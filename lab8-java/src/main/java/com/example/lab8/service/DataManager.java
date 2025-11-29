package com.example.lab8.service;

import com.example.lab8.annotation.DataProcessor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {
    private final List<Object> processors = new ArrayList<>();
    private List<String> data = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(List<String> source) {
        this.data = new ArrayList<>(source);
    }

    public void processData() {
        for (Object processor : processors) {
            for (Method method : processor.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    try {
                        System.out.println("Применяем процессор: " + method.getName());
                        // Предполагаем, что методы принимают List<String> и возвращают List<String>
                        // В реальном Stream API мы бы строили цепочку, здесь имитация вызова через рефлексию
                        @SuppressWarnings("unchecked")
                        List<String> result = (List<String>) method.invoke(processor, data);
                        this.data = result;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public List<String> getData() {
        return data;
    }
}