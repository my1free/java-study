package com.my.base.jar.concurrent;

/**
 * @author michealyang
 * @version 1.0
 * @created 18/7/12
 * 开始眼保健操： →_→  ↑_↑  ←_←  ↓_↓
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        Integer mutex = 1;
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    try {
                        System.out.println("Thread 1 start");
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mutex) {
                    try {
                        System.out.println("Thread 2 start");
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
        System.out.println("finished");
    }
}
