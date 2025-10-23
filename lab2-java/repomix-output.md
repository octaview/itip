This file is a merged representation of the entire codebase, combined into a single document by Repomix.

# File Summary

## Purpose
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.

## File Format
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  a. A header with the file path (## File: path/to/file)
  b. The full contents of the file in a code block

## Usage Guidelines
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.

## Notes
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)

# Directory Structure
```
src/
  main/
    java/
      com/
        example/
          lab2/
            io/
              ConsoleIO.java
            model/
              Car.java
              Motorcycle.java
              Pickup.java
              Sedan.java
              SportBike.java
              Truck.java
              Vehicle.java
            spi/
              Drivable.java
              Maintainable.java
            App.java
build.gradle.kts
README.md
settings.gradle.kts
```

# Files

## File: src/main/java/com/example/lab2/io/ConsoleIO.java
````java
package com.example.lab2.io;

import com.example.lab2.model.Sedan;
import java.util.Scanner;

/**
 * Простейший ввод/вывод для демонстрации требования лабы.
 */
public class ConsoleIO {

    public static Sedan readSedanFromStdin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Марка седана: ");
        String make = sc.nextLine().trim();
        System.out.print("Модель седана: ");
        String model = sc.nextLine().trim();
        System.out.print("Год выпуска: ");
        int year = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Сидений: ");
        int seats = Integer.parseInt(sc.nextLine().trim());
        System.out.print("АКПП? (true/false): ");
        boolean automatic = Boolean.parseBoolean(sc.nextLine().trim());
        System.out.print("Объём багажника (л): ");
        double trunk = Double.parseDouble(sc.nextLine().trim());
        return new Sedan(make, model, year, seats, automatic, trunk);
    }

    public static void printLine() {
        System.out.println("-".repeat(60));
    }
}
````

## File: src/main/java/com/example/lab2/model/Car.java
````java
package com.example.lab2.model;

public class Car extends Vehicle {
    private int seats;
    private boolean automatic;

    public Car(String make, String model, int year, int seats, boolean automatic) {
        super(make, model, year);
        this.seats = seats;
        this.automatic = automatic;
    }

    public Car() {
        this("N/A", "N/A", 0, 5, true);
    }

    @Override
    public String getType() {
        return "Car";
    }

    @Override
    public void drive() {
        System.out.println("Легковой автомобиль едет по дороге.");
    }

    public int getSeats() { return seats; }
    public void setSeats(int seats) { this.seats = seats; }

    public boolean isAutomatic() { return automatic; }
    public void setAutomatic(boolean automatic) { this.automatic = automatic; }
}
````

## File: src/main/java/com/example/lab2/model/Motorcycle.java
````java
package com.example.lab2.model;

public class Motorcycle extends Vehicle {
    private boolean hasAbs;
    private int engineCc;

    public Motorcycle(String make, String model, int year, boolean hasAbs, int engineCc) {
        super(make, model, year);
        this.hasAbs = hasAbs;
        this.engineCc = engineCc;
    }

    public Motorcycle() {
        this("N/A", "N/A", 0, true, 600);
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }

    @Override
    public void drive() {
        System.out.println("Мотоцикл ускоряется и маневрирует.");
    }

    public boolean isHasAbs() { return hasAbs; }
    public void setHasAbs(boolean hasAbs) { this.hasAbs = hasAbs; }

    public int getEngineCc() { return engineCc; }
    public void setEngineCc(int engineCc) { this.engineCc = engineCc; }
}
````

## File: src/main/java/com/example/lab2/model/Pickup.java
````java
package com.example.lab2.model;

public class Pickup extends Truck {
    private boolean doubleCab;

    public Pickup(String make, String model, int year, double payloadTons, int axles, boolean doubleCab) {
        super(make, model, year, payloadTons, axles);
        this.doubleCab = doubleCab;
    }

    public Pickup() {
        this("Ford", "F-150", 2021, 1.2, 2, true);
    }

    @Override
    public String getType() {
        return "Pickup";
    }

    public boolean isDoubleCab() { return doubleCab; }
    public void setDoubleCab(boolean doubleCab) { this.doubleCab = doubleCab; }

    @Override
    public void drive() {
        System.out.println("Пикап уверенно едет по бездорожью.");
    }
}
````

## File: src/main/java/com/example/lab2/model/Sedan.java
````java
package com.example.lab2.model;

public class Sedan extends Car {
    private double trunkVolume;

    public Sedan(String make, String model, int year, int seats, boolean automatic, double trunkVolume) {
        super(make, model, year, seats, automatic);
        this.trunkVolume = trunkVolume;
    }

    public Sedan() {
        this("Toyota", "Camry", 2020, 5, true, 480.0);
    }

    @Override
    public String getType() {
        return "Sedan";
    }

    public double getTrunkVolume() { return trunkVolume; }
    public void setTrunkVolume(double trunkVolume) { this.trunkVolume = trunkVolume; }

    @Override
    public void drive() {
        System.out.println("Седан плавно и комфортно едет по шоссе.");
    }
}
````

## File: src/main/java/com/example/lab2/model/SportBike.java
````java
package com.example.lab2.model;

public class SportBike extends Motorcycle {
    private String ridingMode;

    public SportBike(String make, String model, int year, boolean hasAbs, int engineCc, String ridingMode) {
        super(make, model, year, hasAbs, engineCc);
        this.ridingMode = ridingMode;
    }

    public SportBike() {
        this("Yamaha", "R6", 2019, true, 599, "Sport");
    }

    @Override
    public String getType() {
        return "SportBike";
    }

    public String getRidingMode() { return ridingMode; }
    public void setRidingMode(String ridingMode) { this.ridingMode = ridingMode; }

    @Override
    public void drive() {
        System.out.println("Спортбайк мгновенно откликается на газ.");
    }
}
````

## File: src/main/java/com/example/lab2/model/Truck.java
````java
package com.example.lab2.model;

public class Truck extends Vehicle {
    private double payloadTons;
    private int axles;

    public Truck(String make, String model, int year, double payloadTons, int axles) {
        super(make, model, year);
        this.payloadTons = payloadTons;
        this.axles = axles;
    }

    public Truck() {
        this("N/A", "N/A", 0, 1.0, 2);
    }

    @Override
    public String getType() {
        return "Truck";
    }

    @Override
    public void drive() {
        System.out.println("Грузовик везёт груз.");
    }

    public double getPayloadTons() { return payloadTons; }
    public void setPayloadTons(double payloadTons) { this.payloadTons = payloadTons; }

    public int getAxles() { return axles; }
    public void setAxles(int axles) { this.axles = axles; }
}
````

## File: src/main/java/com/example/lab2/model/Vehicle.java
````java
package com.example.lab2.model;

import com.example.lab2.spi.Drivable;
import com.example.lab2.spi.Maintainable;
import java.util.Objects;

/**
 * Абстрактный базовый класс (Абстракция).
 */
public abstract class Vehicle implements Drivable, Maintainable {

    private String make;
    private String model;
    private int year;

    private static int instanceCount = 0;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
        instanceCount++;
    }

    public Vehicle() {
        this("N/A", "N/A", 0);
    }

    public abstract String getType();

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "%s %s %s (%d)".formatted(getType(), make, model, year);
    }

    public static int getInstanceCount() {
        return instanceCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle v)) return false;
        return year == v.year &&
               Objects.equals(make, v.make) &&
               Objects.equals(model, v.model) &&
               Objects.equals(getType(), v.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, getType());
    }
}
````

## File: src/main/java/com/example/lab2/spi/Drivable.java
````java
package com.example.lab2.spi;

public interface Drivable {
    void drive();
}
````

## File: src/main/java/com/example/lab2/spi/Maintainable.java
````java
package com.example.lab2.spi;

public interface Maintainable {
    default String maintenanceHint() {
        return "Плановое ТО раз в 10 000 км.";
    }
}
````

## File: src/main/java/com/example/lab2/App.java
````java
package com.example.lab2;

import com.example.lab2.io.ConsoleIO;
import com.example.lab2.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрация: наследование (2 уровня), полиморфизм, инкапсуляция,
 * абстракция, статический счётчик и ввод/вывод.
 */
public class App {
    public static void main(String[] args) {
        Vehicle v1 = new Sedan("Hyundai", "Sonata", 2022, 5, true, 510.0);
        Vehicle v2 = new Pickup();     
        Vehicle v3 = new SportBike();  

        List<Vehicle> garage = new ArrayList<>();
        garage.add(v1);
        garage.add(v2);
        garage.add(v3);

        for (Vehicle v : garage) {
            System.out.println(v);
            v.drive();
            System.out.println("Совет по обслуживанию: " + v.maintenanceHint());
            System.out.println();
        }

        ConsoleIO.printLine();
        System.out.println("Счётчик созданных ТС: " + Vehicle.getInstanceCount());
        ConsoleIO.printLine();

        try {
            Sedan fromStdin = ConsoleIO.readSedanFromStdin();
            System.out.println("Введено: " + fromStdin);
            fromStdin.drive();
        } catch (Exception e) {
            System.out.println("Ввод отменён или некорректен: " + e.getMessage());
        }

        ConsoleIO.printLine();
        System.out.println("Итоговый счётчик созданных ТС: " + Vehicle.getInstanceCount());
    }
}
````

## File: build.gradle.kts
````
plugins {
    id("java")
    application
}

group = "com.example"
version = "1.0.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("com.example.lab2.App")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
````

## File: README.md
````markdown
# Лабораторная работа №2 — ООП (Вариант 4: Транспортные средства)

## Что сделано

- Построена иерархия классов:
  - Абстрактный класс `Vehicle` (марка, модель, год) + контракты `drive()`, `getType()`.
  - Первый уровень: `Car`, `Truck`, `Motorcycle`.
  - Второй уровень: `Sedan` (Car), `Pickup` (Truck), `SportBike` (Motorcycle).
- Продемонстрированы принципы ООП: абстракция, инкапсуляция, наследование, полиморфизм.
- Добавлен **статический счётчик** созданных объектов `Vehicle`.
- Реализован ввод/вывод: чтение параметров `Sedan` из консоли и печать сведений.
- Сборка/запуск через **Gradle**.

Требования задания («абстрактный класс», «два уровня наследования», «геттеры/сеттеры», «ввод/вывод», «статический счётчик») соблюдены.  
Источник формулировки ЛР в методичке: раздел 2 «Объектно-ориентированное программирование», блок «Задания для выполнения лабораторной работы». :contentReference[oaicite:5]{index=5}

## Требования

- Java 17+
- Gradle (или используйте Gradle Wrapper, если добавите его командой `gradle wrapper`)

## Сборка и запуск

```bash
# из корня проекта
gradle run
````

## File: settings.gradle.kts
````
rootProject.name = "lab2-vehicles"
````
