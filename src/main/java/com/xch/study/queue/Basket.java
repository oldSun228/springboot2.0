package com.xch.study.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 定义装苹果的篮子
 */
public class Basket {
    // 篮子，能够容纳3个苹果
   static BlockingQueue<String> basket = new ArrayBlockingQueue<>(3);

    // 生产苹果，放入篮子
    public static void produce(String result) throws InterruptedException {
        // put方法放入一个苹果，若basket满了，等到basket有位置
        basket.put(result);
    }

    // 消费苹果，从篮子中取走
    public static String consume(String result) throws InterruptedException {
        // get方法取出一个苹果，若basket为空，等到basket有苹果为止
        String apple = basket.take();
        return apple;
    }

    public static int getAppleNumber() {
        return basket.size();
    }
}
