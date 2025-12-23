package com.example.lab6;

import com.example.lab6.sales.SalesTracker;
import com.example.lab6.structure.Stack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in, "UTF-8");
        while (true) {
            System.out.println("\n=== Лаба 6 — меню ===");
            System.out.println("1) Топ-10 слов из файла");
            System.out.println("2) Тест стека");
            System.out.println("3) Учёт продаж");
            System.out.println("0) Выход");
            System.out.print("Выбор: ");

            String choice = in.nextLine().trim();
            switch (choice) {
                case "1" -> runTopWords(in);
                case "2" -> runStackDemo();
                case "3" -> runSalesDemo(in);
                case "0" -> {
                    System.out.println("Пока!");
                    return;
                }
                default -> System.out.println("Неизвестный пункт. Попробуй ещё раз.");
            }
        }
    }

    // ===== Задание 1: Топ-10 слов из файла =====
    private static void runTopWords(Scanner in) {
        try {
            System.out.print("Укажи путь к файлу (по умолчанию: text.txt): ");
            String path = in.nextLine().trim();
            if (path.isEmpty()) path = "text.txt";

            System.out.print("Сколько слов вывести в топе? (Enter = 10): ");
            String nStr = in.nextLine().trim();
            int topN = nStr.isEmpty() ? 10 : Integer.parseInt(nStr);

            printTopWords(new File(path), topN);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден. Проверь путь.");
        } catch (NumberFormatException e) {
            System.err.println("Некорректное число. Повтори выбор меню.");
        }
    }

    private static void printTopWords(File file, int topN) throws FileNotFoundException {
        Map<String, Integer> counts = new HashMap<>();
        try (Scanner sc = new Scanner(file, "UTF-8")) {
            // Разделяем по небуквенно-цифровым символам (поддержка Юникода)
            sc.useDelimiter("[^\\p{L}\\p{Nd}]+");
            while (sc.hasNext()) {
                String w = sc.next().toLowerCase(Locale.ROOT);
                if (!w.isEmpty()) counts.put(w, counts.getOrDefault(w, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(counts.entrySet());
        list.sort((a, b) -> {
            int c = Integer.compare(b.getValue(), a.getValue());
            return (c != 0) ? c : a.getKey().compareTo(b.getKey());
        });

        System.out.println("\nТоп слов (" + Math.min(topN, list.size()) + "):");
        for (int i = 0; i < Math.min(topN, list.size()); i++) {
            var e = list.get(i);
            System.out.printf("%2d. %s: %d%n", i + 1, e.getKey(), e.getValue());
        }
    }

    // ===== Задание 2: Стек =====
    private static void runStackDemo() {
        System.out.println("\n=== Тест Стека ===");
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Pop: " + stack.pop()); // 3
        System.out.println("Peek: " + stack.peek()); // 2
        System.out.println("Size: " + stack.size());
        System.out.println("Empty? " + stack.isEmpty());
        stack.pop(); stack.pop();
        System.out.println("Empty after pops? " + stack.isEmpty());
    }

    // ===== Задание 3: Учёт продаж =====
    private static void runSalesDemo(Scanner in) {
        System.out.println("\n=== Учёт продаж ===");
        SalesTracker tracker = new SalesTracker();

        System.out.println("Вводи продажи. Пустое название — завершить ввод.");
        while (true) {
            System.out.print("Товар (пусто — конец): ");
            String item = in.nextLine().trim();
            if (item.isEmpty()) break;

            try {
                System.out.print("Количество (целое > 0): ");
                int qty = Integer.parseInt(in.nextLine().trim());
                System.out.print("Цена за единицу (>= 0): ");
                double price = Double.parseDouble(in.nextLine().trim());

                tracker.addSale(item, qty, price);
            } catch (NumberFormatException e) {
                System.err.println("Неверный формат числа. Продажа пропущена.");
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }

        // Вывод результатов
        System.out.println("\nСписок продаж:");
        tracker.listSales().forEach(System.out::println);

        System.out.printf(Locale.ROOT, "%nИтого: %.2f%n", tracker.totalAmount());
        System.out.println("Самый популярный товар: " +
                tracker.mostPopularItem().orElse("—"));
    }
}
