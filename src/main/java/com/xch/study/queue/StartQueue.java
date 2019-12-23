package com.xch.study.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartQueue {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        service.submit(producer);
        service.submit(consumer);
        // 程序运行10s后，所有任务停止
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdownNow();
    }

}
