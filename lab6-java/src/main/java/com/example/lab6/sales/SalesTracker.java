package com.example.lab6.sales;

import java.util.*;
import java.util.stream.Collectors;

public class SalesTracker {
    public static final class Sale {
        private final String item;
        private final int qty;
        private final double price;

        public Sale(String item, int qty, double price) {
            if (item == null || item.trim().isEmpty())
                throw new IllegalArgumentException("item is required");
            if (qty <= 0) throw new IllegalArgumentException("qty must be > 0");
            if (price < 0) throw new IllegalArgumentException("price must be >= 0");
            this.item = item.trim();
            this.qty = qty;
            this.price = price;
        }

        public String item() { return item; }
        public int qty() { return qty; }
        public double price() { return price; }
        public double amount() { return qty * price; }

        @Override public String toString() {
            return item + " x" + qty + " @" + String.format(Locale.ROOT, "%.2f", price)
                   + " = " + String.format(Locale.ROOT, "%.2f", amount());
        }
    }

    private final List<Sale> sales = new ArrayList<>();

    public void addSale(String item, int qty, double price) {
        sales.add(new Sale(item, qty, price));
    }

    public List<Sale> listSales() {
        return Collections.unmodifiableList(sales);
    }

    public double totalAmount() {
        return sales.stream().mapToDouble(Sale::amount).sum();
    }

    public Optional<String> mostPopularItem() {
        return sales.stream()
                .collect(Collectors.groupingBy(Sale::item, Collectors.summingInt(Sale::qty)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }
}
