package com.example.lab4;

import com.example.lab4.exception.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        System.out.println("=== Задание 1A: Массив строк ===");
        String[] strArray = {"10", "20", "30", "40", "invalid", "50"};
        try {
            double avg = calculateAverageFromStrings(strArray);
            System.out.println("Среднее: " + avg);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка формата числа: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Арифметическая ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Неизвестная ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Задание 1B: Массив int с индексами ===");
        int[] ints = {1, 2, 3, 4, 5};
        try {
            double avg2 = calculateAverageIndexed(ints, 7); // намеренно берём length=7
            System.out.println("Среднее: " + avg2);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Выход за границы массива: " + e.getMessage());
            logException(e);
        } catch (ArithmeticException e) {
            System.err.println("Арифметическая ошибка: " + e.getMessage());
            logException(e);
        }

        System.out.println("\n=== Задание 2: Копирование файла ===");
        try {
            copyFile("input.txt", "output.txt");
            System.out.println("Копирование завершено.");
        } catch (CustomFileNotFoundException | CustomIOReadWriteException e) {
            System.err.println("Ошибка копирования: " + e.getMessage());
            logException(e);
        }

        System.out.println("\n=== Задание 3: Набор пользовательских исключений ===");

        // Деление
        try {
            System.out.println("10 / 0 = " + safeDivide(10, 0));
        } catch (CustomDivisionException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            logException(e);
        }

        // Возраст
        try {
            validateAge(150);
        } catch (CustomAgeException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            logException(e);
        }

        // Файл не найден
        try {
            readFirstLine("no_such_file.txt");
        } catch (CustomFileNotFoundException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            logException(e);
        }

        // Неправильный формат числа
        try {
            parseIntStrict("12x3");
        } catch (CustomNumberFormatException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            logException(e);
        }

        // Пустой стек
        try {
            CustomStack<String> stack = new CustomStack<>();
            stack.pop();
        } catch (CustomEmptyStackException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            logException(e);
        }

        // Неверный ввод с клавиатуры (эмулируем)
        try {
            simulateReadInt("abc");
        } catch (CustomInputMismatchException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            logException(e);
        }

        // Неверный email
        try {
            validateEmail("foo@@bar");
        } catch (CustomEmailFormatException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            logException(e);
        }

        // Неподдерживаемая операция
        try {
            double r = calc(2, 3, "%"); // нет операции %
            System.out.println("Результат: " + r);
        } catch (CustomUnsupportedOperationException e) {
            System.err.println("Перехвачено: " + e.getMessage());
            logException(e);
        }
    }

    // ===== Задание 1A (строки) =====
    public static double calculateAverageFromStrings(String[] arr) {
        int sum = 0, count = 0;
        for (String s : arr) {
            try {
                sum += Integer.parseInt(s);
                count++;
            } catch (NumberFormatException e) {
                System.out.println("Элемент '" + s + "' не является числом, пропускаем.");
            }
        }
        if (count == 0) throw new ArithmeticException("Нет валидных элементов для расчёта.");
        return (double) sum / count;
    }

    // ===== Задание 1B (индексы -> ловим выход за границы) =====
    public static double calculateAverageIndexed(int[] arr, int lengthToUse) {
        long sum = 0;
        for (int i = 0; i < lengthToUse; i++) { // может бросить ArrayIndexOutOfBoundsException
            sum += arr[i];
        }
        if (lengthToUse == 0) throw new ArithmeticException("Длина = 0");
        return (double) sum / lengthToUse;
    }

    // ===== Задание 2 (копирование файла) =====
    public static void copyFile(String src, String dst)
            throws CustomFileNotFoundException, CustomIOReadWriteException {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dst))) {
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) != -1) {
                out.write(buf, 0, n);
            }
        } catch (FileNotFoundException e) {
            throw new CustomFileNotFoundException("Файл не найден: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new CustomIOReadWriteException("Ошибка чтения/записи: " + e.getMessage(), e);
        }
    }

    public static double safeDivide(double a, double b) throws CustomDivisionException {
        if (b == 0.0) throw new CustomDivisionException("Деление на ноль: " + a + " / " + b);
        return a / b;
    }

    public static void validateAge(int age) throws CustomAgeException {
        if (age < 0 || age > 120) throw new CustomAgeException("Недопустимый возраст: " + age);
        System.out.println("Возраст корректен: " + age);
    }

    public static String readFirstLine(String path) throws CustomFileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (FileNotFoundException e) {
            throw new CustomFileNotFoundException("Не найден файл: " + path, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int parseIntStrict(String s) throws CustomNumberFormatException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException("Строка не число: '" + s + "'", e);
        }
    }

    public static int simulateReadInt(String simulatedInput) throws CustomInputMismatchException {
        try (Scanner sc = new Scanner(simulatedInput)) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                throw new CustomInputMismatchException("Ожидалось целое число", e);
            }
        }
    }

    public static void validateEmail(String email) throws CustomEmailFormatException {
        if (email == null || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new CustomEmailFormatException("Неверный формат email: " + email);
        }
        System.out.println("Email ok: " + email);
    }

    public static double calc(double a, double b, String op) throws CustomUnsupportedOperationException {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new CustomUnsupportedOperationException("Деление на 0 не поддерживается");
                return a / b;
            default:
                throw new CustomUnsupportedOperationException("Операция не поддерживается: " + op);
        }
    }

    public static void logException(Exception e) {
        try (FileWriter fw = new FileWriter("exceptions.log", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("LOG [" + java.time.LocalDateTime.now() + "]: " +
                    e.getClass().getName() + " - " + e.getMessage());
        } catch (IOException ioEx) {
            System.err.println("Не удалось записать лог: " + ioEx.getMessage());
        }
    }
}
