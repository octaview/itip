package com.example.lab8;

import com.example.lab8.service.DataManager;
import com.example.lab8.service.StringFilter;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<String> rawData = Arrays.asList("java", "is", "cool", "stream", "api", "annotation");
        
        DataManager dataManager = new DataManager();
        
        // 1. Регистрируем обработчик
        dataManager.registerDataProcessor(new StringFilter());
        
        // 2. Загружаем данные
        dataManager.loadData(rawData);
        System.out.println("Исходные данные: " + rawData);

        // 3. Обрабатываем (используя рефлексию и Stream API внутри метода)
        dataManager.processData();

        // 4. Результат
        System.out.println("Результат: " + dataManager.getData());
    }
}