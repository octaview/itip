package com.example.lab4;

import com.example.lab4.exception.CustomAgeException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) {
        // --- Задание 1: Среднее арифметическое массива ---
        System.out.println("=== Задание 1: Массив ===");
        String[] strArray = {"10", "20", "30", "40", "invalid", "50"};
        try {
            double avg = calculateAverage(strArray);
            System.out.println("Среднее: " + avg);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка формата числа: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Арифметическая ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Неизвестная ошибка: " + e.getMessage());
        }

        // --- Задание 3: CustomAgeException и логирование ---
        System.out.println("\n=== Задание 3: Возраст ===");
        try {
            validateAge(150);
        } catch (CustomAgeException e) {
            System.err.println("Перехвачено исключение: " + e.getMessage());
            logException(e);
        }
    }

    // Метод для Задания 1
    public static double calculateAverage(String[] arr) {
        int sum = 0;
        int count = 0;
        for (String s : arr) {
            try {
                sum += Integer.parseInt(s);
                count++;
            } catch (NumberFormatException e) {
                System.out.println("Элемент '" + s + "' не является числом, пропускаем.");
            }
        }
        if (count == 0) throw new ArithmeticException("Нет валидных элементов для расчета.");
        return (double) sum / count;
    }

    // Метод для Задания 3
    public static void validateAge(int age) throws CustomAgeException {
        if (age < 0 || age > 120) {
            throw new CustomAgeException("Недопустимый возраст: " + age);
        }
        System.out.println("Возраст корректен: " + age);
    }

    // Логирование в файл
    public static void logException(Exception e) {
        try (FileWriter fw = new FileWriter("exceptions.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("LOG [" + java.time.LocalDateTime.now() + "]: " + e.getClass().getName() + " - " + e.getMessage());
        } catch (IOException ioEx) {
            System.err.println("Не удалось записать лог: " + ioEx.getMessage());
        }
    }
}