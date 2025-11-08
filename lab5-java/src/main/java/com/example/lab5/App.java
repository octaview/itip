package com.example.lab5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        // --- Задание 1: Поиск чисел ---
        System.out.println("=== Поиск чисел ===");
        String text = "The price is $19.99, discount 20%, total 15.99.";
        findNumbers(text);

        // --- Задание 2: Проверка пароля ---
        System.out.println("\n=== Проверка пароля ===");
        checkPassword("Password123"); // Good
        checkPassword("pass");        // Bad (short)
        checkPassword("PASSWORD123"); // Bad (no lower)

        // --- Задание 4: Проверка IP ---
        System.out.println("\n=== Проверка IP ===");
        checkIP("192.168.0.1");   // Valid
        checkIP("256.0.0.1");     // Invalid
        checkIP("abc.def.ghi.jkl"); // Invalid
    }

    private static void findNumbers(String text) {
        // Ищем целые и дробные числа
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено число: " + matcher.group());
        }
    }

    private static void checkPassword(String password) {
        // 8-16 символов, латиница, цифры, минимум 1 заглавная, 1 цифра
        String regex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
        boolean isValid = Pattern.matches(regex, password);
        System.out.println("Пароль '" + password + "' корректен? -> " + isValid);
    }

    private static void checkIP(String ip) {
        // Простая проверка формата: (0-255).(0-255).(0-255).(0-255)
        // Более строгий regex для 0-255: (25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)
        String num = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
        
        boolean isValid = Pattern.matches(regex, ip);
        System.out.println("IP '" + ip + "' корректен? -> " + isValid);
    }
}