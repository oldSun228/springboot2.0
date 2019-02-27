package com.xch.study.biz.queueClassDemo.controller;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author fgs
 * @data 2019/1/18 16:03
 */
public class QueueDemo1 {
    public static void main(String[] args) {
        Queue<Character> qc = new LinkedList<>();
        for (char o : "fanguoshun".toCharArray()) {
            qc.offer(o);
        }
        QueueDemo.printQ(qc);
    }


}

class QueueDemo {
    public static void printQ(Queue queue) {
        while (queue.peek() != null) {
            System.out.println(queue.remove());
        }
    }
}