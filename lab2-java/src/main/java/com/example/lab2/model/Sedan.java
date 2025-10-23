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
