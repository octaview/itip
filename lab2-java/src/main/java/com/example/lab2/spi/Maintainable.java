package com.example.lab2.spi;

public interface Maintainable {
    default String maintenanceHint() {
        return "Плановое ТО раз в 10 000 км.";
    }
}
