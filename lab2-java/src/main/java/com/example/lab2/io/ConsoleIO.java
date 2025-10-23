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
