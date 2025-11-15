package com.example.lab6;

import com.example.lab6.structure.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {
    public static void main(String[] args) {
        // --- Задание 2: Стек ---
        System.out.println("=== Тест Стека ===");
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Pop: " + stack.pop()); // 3
        System.out.println("Peek: " + stack.peek()); // 2

        // --- Задание 1: Топ-10 слов ---
        System.out.println("\n=== Топ-10 слов ===");
        // Для примера используем строку вместо файла, чтобы код был самодостаточным
        String content = "Java is fun. Java is powerful. Collections in Java are useful. Java Java Java.";
        processTopWords(content);
    }

    private static void processTopWords(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();
        // Разбиваем по пробелам и убираем знаки препинания
        Scanner scanner = new Scanner(text);
        scanner.useDelimiter("[^a-zA-Z]+");

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }
        scanner.close();

        // Сортировка
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCounts.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue())); // По убыванию

        System.out.println("Топ слов:");
        for (int i = 0; i < Math.min(10, list.size()); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getKey() + ": " + list.get(i).getValue());
        }
    }
}