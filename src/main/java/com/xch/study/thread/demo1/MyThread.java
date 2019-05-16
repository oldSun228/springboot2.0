package com.xch.study.thread.demo1;

/**
 * @author fgs
 * @data 2019/4/8 20:05
 */
public class MyThread extends Thread {
    public MyThread(Runnable runnable) {
        super(runnable);
    }

    @Override
    public void run() {
        System.out.println("in MyThread run");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
