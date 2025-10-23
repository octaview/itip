package com.example.lab2.model;

public class Motorcycle extends Vehicle {
    private boolean hasAbs;
    private int engineCc;

    public Motorcycle(String make, String model, int year, boolean hasAbs, int engineCc) {
        super(make, model, year);
        this.hasAbs = hasAbs;
        this.engineCc = engineCc;
    }

    public Motorcycle() {
        this("N/A", "N/A", 0, true, 600);
    }

    @Override
    public String getType() {
        return "Motorcycle";
    }

    @Override
    public void drive() {
        System.out.println("Мотоцикл ускоряется и маневрирует.");
    }

    public boolean isHasAbs() { return hasAbs; }
    public void setHasAbs(boolean hasAbs) { this.hasAbs = hasAbs; }

    public int getEngineCc() { return engineCc; }
    public void setEngineCc(int engineCc) { this.engineCc = engineCc; }
}
