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
