package com.example.lab3;

import com.example.lab3.io.ConsoleIO;
import com.example.lab3.model.AutoPark;
import com.example.lab3.model.CarRecord;
import com.example.lab3.util.HashTable;

public class App {
    public static void main(String[] args) {
        // === ЧАСТЬ 1: собственная HashTable<K,V> ===
        HashTable<String, Integer> scores = new HashTable<>();
        scores.put("alice", 10);
        scores.put("bob", 15);
        scores.put("alice", 20); // обновление значения по существующему ключу

        System.out.println("scores[alice] = " + scores.get("alice"));
        System.out.println("scores[bob]   = " + scores.get("bob"));
        System.out.println("size=" + scores.size() + ", empty=" + scores.isEmpty());
        System.out.println("remove[bob] -> " + scores.remove("bob"));
        System.out.println("size=" + scores.size());

        ConsoleIO.line();

        // === ЧАСТЬ 2: работа с HashMap (автопарк, ключ — госномер) ===
        AutoPark park = new AutoPark();

        try {
            String plate1 = "A111AA77";
            park.upsert(plate1, new CarRecord("Toyota", "Camry", 2020));
            String plate2 = "B222BB77";
            park.upsert(plate2, new CarRecord("Ford", "Focus", 2018));
            System.out.println("Текущий реестр: " + park);

            // Ввод одной записи с консоли
            ConsoleIO.line();
            String plate = ConsoleIO.readPlate();
            CarRecord rec  = ConsoleIO.readCarRecord();
            park.upsert(plate, rec);

            ConsoleIO.line();
            System.out.println("Найти по ключу '" + plate + "': " + park.find(plate));
            System.out.println("Всего записей: " + park.size());

            // Удаление
            ConsoleIO.line();
            System.out.println("Удаляем '" + plate2 + "': " + park.remove(plate2));
            System.out.println("Реестр после удаления: " + park);
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
}
