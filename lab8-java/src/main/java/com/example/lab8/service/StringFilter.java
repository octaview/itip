package com.example.lab8.service;

import com.example.lab8.annotation.DataProcessor;
import java.util.List;
import java.util.stream.Collectors;

public class StringFilter {

    @DataProcessor(description = "Filter out short words and uppercase remaining")
    public List<String> filterAndTransform(List<String> input) {
        return input.stream()
                .filter(s -> s.length() > 3) // Фильтр: длина > 3
                .map(String::toUpperCase)    // Преобразование: в верхний регистр
                .sorted()                    // Сортировка
                .collect(Collectors.toList());
    }
}