package com.xch.study.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueController {
    /**
     * 定义装苹果的篮子
     */
    public static class Basket {
        // 篮子，能够容纳3个苹果
        BlockingQueue<String> basket = new ArrayBlockingQueue<>(3);

        // 生产苹果，放入篮子
        public void produce() throws InterruptedException {
            // put方法放入一个苹果，若basket满了，等到basket有位置
            basket.put("An apple");
        }

        // 消费苹果，从篮子中取走
        public String consume() throws InterruptedException {
            // get方法取出一个苹果，若basket为空，等到basket有苹果为止
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + basket.element());
            String apple = basket.take();
            return apple;
        }

        public int getAppleNumber() {
            return basket.size();
        }

    }

    //　测试方法
    public static void testBasket() {
        // 建立一个装苹果的篮子
        final Basket basket = new Basket();
        // 定义苹果生产者
        class Producer implements Runnable {
            public void run() {
                try {
                    while (true) {
                        // 生产苹果
                        basket.produce();
                        System.out.println("生产完后有苹果：" + basket.getAppleNumber() + "个");
                        // 休眠300ms
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        // 定义苹果消费者
        class Consumer implements Runnable {
            public void run() {
                try {
                    while (true) {
                        // 消费苹果
                        String re = basket.consume();
                        System.out.println(">>>>>>>>>>>>>>>>>>" + re);
                        System.out.println("消费完后有苹果：" + basket.getAppleNumber() + "个");
                        // 休眠1000ms
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        ExecutorService service = Executors.newCachedThreadPool();
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        service.submit(producer);
        service.submit(consumer);
        // 程序运行10s后，所有任务停止
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
        }
        service.shutdownNow();
    }

    public static void main(String[] args) {
        BlockingQueueController.testBasket();
    }
}