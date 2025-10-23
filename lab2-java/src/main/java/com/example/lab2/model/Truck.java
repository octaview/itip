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
