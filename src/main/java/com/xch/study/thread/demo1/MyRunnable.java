package com.xch.study.thread.demo1;

/**
 * @author fgs
 * @data 2019/4/8 20:05
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("in MyRunnable run");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}
