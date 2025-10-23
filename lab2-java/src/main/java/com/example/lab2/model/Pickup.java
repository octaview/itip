package com.example.lab2.model;

public class Pickup extends Truck {
    private boolean doubleCab;

    public Pickup(String make, String model, int year, double payloadTons, int axles, boolean doubleCab) {
        super(make, model, year, payloadTons, axles);
        this.doubleCab = doubleCab;
    }

    public Pickup() {
        this("Ford", "F-150", 2021, 1.2, 2, true);
    }

    @Override
    public String getType() {
        return "Pickup";
    }

    public boolean isDoubleCab() { return doubleCab; }
    public void setDoubleCab(boolean doubleCab) { this.doubleCab = doubleCab; }

    @Override
    public void drive() {
        System.out.println("Пикап уверенно едет по бездорожью.");
    }
}
