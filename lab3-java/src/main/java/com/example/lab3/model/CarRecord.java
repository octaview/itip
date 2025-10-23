package com.example.lab3.model;

import java.util.Objects;

public class CarRecord {
    private String brand;
    private String model;
    private int year;

    public CarRecord(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public CarRecord() { this("N/A", "N/A", 0); }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "%s %s (%d)".formatted(brand, model, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarRecord that)) return false;
        return year == that.year &&
               Objects.equals(brand, that.brand) &&
               Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year);
    }
}
