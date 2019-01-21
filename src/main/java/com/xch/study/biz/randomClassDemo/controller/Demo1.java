package com.xch.study.biz.randomClassDemo.controller;

import java.util.Random;

/**
 * @author fgs
 * @data 2019/1/18 16:03
 */
public class Demo1 {
    public static void main(String[] args) {
//        Random random1 = new Random(10);
//        Random random2 = new Random(10);
//        System.out.println(random1.nextInt()); // -1157793070
//        System.out.println(random2.nextInt()); // -1157793070


        Random random = new Random(10);
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
