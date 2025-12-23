package com.example.lab7;

import java.util.Arrays;
import java.util.concurrent.*;

public class ArraySumTask {
    public static void run() throws InterruptedException, ExecutionException {
        int[] array = new int[1_000_000];
        Arrays.fill(array, 1); 

        int threads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        
        int chunkSize = array.length / threads;
        Future<Long>[] results = new Future[threads];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threads; i++) {
            final int start = i * chunkSize;
            final int end = (i == threads - 1) ? array.length : (i + 1) * chunkSize;
            
            results[i] = executor.submit(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    sum += array[j];
                }
                return sum;
            });
        }

        long totalSum = 0;
        for (Future<Long> future : results) {
            totalSum += future.get();
        }

        executor.shutdown();
        
        long endTime = System.currentTimeMillis();
        System.out.println("Сумма: " + totalSum); // Должно быть 1 000 000
        System.out.println("Время: " + (endTime - startTime) + " мс");
    }
}