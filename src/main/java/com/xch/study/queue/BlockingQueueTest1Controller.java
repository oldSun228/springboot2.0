package com.xch.study.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Author fgs
 * @Date 2019/12/20 13:49
 * @Version 1.0
 * @Description
 **/
public class BlockingQueueTest1Controller {
    static BlockingQueue<String> basket = new ArrayBlockingQueue<>(3);

    public static void main(String[] args) {
        for (int i = 1; i < 21; i++) {
            basket.offer(">>>>>>" + i);
        }
    }
}
