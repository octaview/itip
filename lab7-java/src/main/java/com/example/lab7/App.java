package com.example.lab7;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа №7 ===");
        
        // Задание 1
        System.out.println("\n--- Задание 1: Сумма элементов массива ---");
        try {
            ArraySumTask.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Задание 2
        System.out.println("\n--- Задание 2: Максимум в матрице ---");
        try {
            MatrixMaxTask.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Задание 3
        System.out.println("\n--- Задание 3: Склад и грузчики (CyclicBarrier) ---");
        try {
            WarehouseTask.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}