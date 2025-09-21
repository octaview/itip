package org.example;

public class Primes {

    public static void main(String[] args) {
        for (int n = 2; n <= 100; n++) {
            if (isPrime(n)) {
                System.out.print(n + " ");
            }
        }
        System.out.println();
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        int limit = (int) Math.sqrt(n);
        for (int d = 3; d <= limit; d += 2) {
            if (n % d == 0) return false;
        }
        return true;
    }
}
