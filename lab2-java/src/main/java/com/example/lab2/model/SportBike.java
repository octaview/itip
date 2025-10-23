package com.example.lab2.model;

public class SportBike extends Motorcycle {
    private String ridingMode;

    public SportBike(String make, String model, int year, boolean hasAbs, int engineCc, String ridingMode) {
        super(make, model, year, hasAbs, engineCc);
        this.ridingMode = ridingMode;
    }

    public SportBike() {
        this("Yamaha", "R6", 2019, true, 599, "Sport");
    }

    @Override
    public String getType() {
        return "SportBike";
    }

    public String getRidingMode() { return ridingMode; }
    public void setRidingMode(String ridingMode) { this.ridingMode = ridingMode; }

    @Override
    public void drive() {
        System.out.println("Спортбайк мгновенно откликается на газ.");
    }
}
