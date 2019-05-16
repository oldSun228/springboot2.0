package com.xch.study.thread.demo1;

/**
 * @author fgs
 * @data 2019/4/8 20:05
 */
public class ThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Runnable myRunnable = new MyRunnable();
                Thread thread = new MyThread(myRunnable);
                thread.start();
            }
        }
    }
}
