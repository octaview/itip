package com.example.lab7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.ArrayList;

public class WarehouseTask {
    private static final AtomicInteger currentBatchWeight = new AtomicInteger(0);
    private static final int MAX_WEIGHT = 150;
    private static final int TOTAL_ITEMS_TO_PROCESS = 15;

    public static void run() {
        ConcurrentLinkedQueue<Integer> items = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < TOTAL_ITEMS_TO_PROCESS; i++) {
            items.add(10 + (int)(Math.random() * 40));
        }
        System.out.println("На складе " + items.size() + " товаров.");

        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println(">>> ГРУЗ СОБРАН (" + currentBatchWeight.get() + " кг). Грузчики везут товары на другой склад...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("<<< Грузчики вернулись.");
            currentBatchWeight.set(0);
        });

        List<Thread> workers = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Thread t = new Thread(new Loader(i, items, barrier));
            workers.add(t);
            t.start();
        }

        for (Thread t : workers) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Работа склада завершена.");
    }

    static class Loader implements Runnable {
        private final int id;
        private final ConcurrentLinkedQueue<Integer> items;
        private final CyclicBarrier barrier;

        public Loader(int id, ConcurrentLinkedQueue<Integer> items, CyclicBarrier barrier) {
            this.id = id;
            this.items = items;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer itemWeight = null;
                    
                    synchronized (currentBatchWeight) {
                        if (items.isEmpty() && currentBatchWeight.get() == 0) {
                            break;
                        }

                        if (currentBatchWeight.get() >= MAX_WEIGHT) {
                        } else {
                            Integer peekItem = items.peek();
                            if (peekItem != null) {
                                if (currentBatchWeight.get() + peekItem > MAX_WEIGHT) {
                                    if (currentBatchWeight.get() < MAX_WEIGHT) {
                                         currentBatchWeight.set(MAX_WEIGHT); 
                                    }
                                } else {
                                    itemWeight = items.poll();
                                    if (itemWeight != null) {
                                        currentBatchWeight.addAndGet(itemWeight);
                                        System.out.println("Грузчик " + id + " взял товар " + itemWeight + " кг. Всего: " + currentBatchWeight.get());
                                    }
                                }
                            } else {
                                if (currentBatchWeight.get() > 0) {
                                    currentBatchWeight.set(MAX_WEIGHT);
                                } else {
                                    break;
                                }
                            }
                        }
                    }

                    if (currentBatchWeight.get() >= MAX_WEIGHT) {
                        System.out.println("Грузчик " + id + " готов к отправке.");
                        barrier.await();
                    } else {
                        Thread.sleep(100); 
                    }
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}