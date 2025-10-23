package com.example.lab3.io;

import java.util.Scanner;

import com.example.lab3.model.CarRecord;

public class ConsoleIO {
    private static final Scanner SC = new Scanner(System.in);

    public static CarRecord readCarRecord() {
        System.out.print("Марка: ");
        String brand = SC.nextLine().trim();
        System.out.print("Модель: ");
        String model = SC.nextLine().trim();
        System.out.print("Год: ");
        int year = Integer.parseInt(SC.nextLine().trim());
        return new CarRecord(brand, model, year);
    }

    public static String readPlate() {
        System.out.print("Госномер (ключ): ");
        return SC.nextLine().trim();
    }

    public static void line() { System.out.println("-".repeat(60)); }
}
