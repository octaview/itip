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
