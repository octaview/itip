package com.example.lab7;

import java.util.Random;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class MatrixMaxTask {
    public static void run() throws InterruptedException, ExecutionException {
        int rows = 1000;
        int cols = 1000;
        int[][] matrix = new int[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(100_000);
            }
        }
        int expectedMax = 999_999;
        matrix[rows / 2][cols / 2] = expectedMax;

        System.out.println("Матрица " + rows + "x" + cols + " сгенерирована.");

        int threadCount = 4;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        
        List<Future<Integer>> futures = new ArrayList<>();
        int rowsPerThread = rows / threadCount;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            final int startRow = i * rowsPerThread;
            final int endRow = (i == threadCount - 1) ? rows : (i + 1) * rowsPerThread;

            futures.add(executor.submit(() -> {
                int localMax = Integer.MIN_VALUE;
                for (int r = startRow; r < endRow; r++) {
                    for (int c = 0; c < cols; c++) {
                        if (matrix[r][c] > localMax) {
                            localMax = matrix[r][c];
                        }
                    }
                }
                return localMax;
            }));
        }

        int globalMax = Integer.MIN_VALUE;
        for (Future<Integer> future : futures) {
            int taskMax = future.get();
            if (taskMax > globalMax) {
                globalMax = taskMax;
            }
        }

        executor.shutdown();
        long endTime = System.currentTimeMillis();

        System.out.println("Найденный максимум: " + globalMax);
        System.out.println("Время выполнения: " + (endTime - startTime) + " мс");
        
        if (globalMax != expectedMax) {
            System.err.println("Ошибка! Найден неверный максимум.");
        }
    }
}