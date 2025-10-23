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
