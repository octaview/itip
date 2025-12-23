package com.example.lab5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Поиск чисел ===");
        String text = "The price is $19.99, discount -20%, total 15.99 and -0.5.";
        findNumbers(text);

        System.out.println("\n=== Проверка пароля ===");
        checkPassword("Password123");
        checkPassword("pass");
        checkPassword("PASSWORD123");

        System.out.println("\n=== Проверка IP ===");
        checkIP("192.168.0.1");
        checkIP("256.0.0.1");
        checkIP("abc.def.ghi.jkl");

        System.out.println("\n=== Задание 3: Заглавная сразу после строчной ===");
        String camelLike = "helloWorld ThisIsTest aBC dEf ghiJ";
        System.out.println("Исходный: " + camelLike);
        System.out.println("Подсветка: " + highlightLowerThenUpper(camelLike));

        System.out.println("\n=== Задание 5: Слова на заданную букву ===");
        String words = "Tree, train, track; apple Taxi turtle top-tier tool t9!";
        findWordsStartingWith(words, 't');
    }

    private static void findNumbers(String text) {
        Pattern pattern = Pattern.compile("-?\\d+(?:\\.\\d+)?");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено число: " + matcher.group());
        }
    }

    private static void checkPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$";
        boolean isValid = Pattern.matches(regex, password);
        System.out.println("Пароль '" + password + "' корректен? -> " + isValid);
    }

    private static void checkIP(String ip) {
        String num = "(25[0-5]|2[0-4][0-9]|1\\d{2}|[1-9]?\\d)";
        String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
        boolean isValid = Pattern.matches(regex, ip);
        System.out.println("IP '" + ip + "' корректен? -> " + isValid);
    }

    private static String highlightLowerThenUpper(String s) {
        Pattern p = Pattern.compile("([\\p{Ll}])([\\p{Lu}])");
        Matcher m = p.matcher(s);
        return m.replaceAll("!$1$2!");
    }

    private static void findWordsStartingWith(String text, char letter) {
        String patternStr = "(?iu)\\b" + Pattern.quote(String.valueOf(letter))
                + "[\\p{L}\\p{Mn}\\p{Pd}']*";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(text);
        boolean any = false;
        while (matcher.find()) {
            System.out.println("Слово: " + matcher.group());
            any = true;
        }
        if (!any) {
            System.out.println("Совпадений не найдено.");
        }
    }
}
