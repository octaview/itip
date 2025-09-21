# Лабораторная работа №1 (Java)

## Состав
- `JavaHelloWorldProgram` — вывод "Hello World".
- `Primes` — все простые числа ≤ 100.
- `Palindrome` — проверка палиндромов из аргументов командной строки.

## Требования среды
- Java 17+
- Maven 3.9+

## Сборка
```bash
mvn -q -e -DskipTests package
```

## Запуск
Hello World:
```bash
mvn -q exec:java -Dexec.mainClass=org.example.JavaHelloWorldProgram
```

Простые числа:
```bash
mvn -q exec:java -Dexec.mainClass=org.example.Primes
# Ожидаемый вывод:
# 2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97
```

Палиндромы:
```bash
mvn -q exec:java -Dexec.mainClass=org.example.Palindrome -Dexec.args="madam racecar apple kayak song noon"
```

## Структура проекта
```
lab1-java/
  pom.xml
  src/main/java/org/example/
    JavaHelloWorldProgram.java
    Primes.java
    Palindrome.java
```
