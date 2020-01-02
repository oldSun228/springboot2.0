package com.xch.study.jdk_api.java8_Stream.controller;

import com.xch.study.jdk_api.java8_Stream.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author fgs
 * @Date 2020/1/2 14:02
 * @Version 1.0
 * @Description
 **/
public class ParallelStreamController {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            test2();
        }
    }

    public static void test1() {
        //初始化一个list
        List<Student> dataList = new ArrayList<>();
        Student entity;
        for (int i = 1; i < 5000; i++) {
            entity = new Student();
            entity.setId(i);
            entity.setName("name" + i);
            entity.setAge(i + 20);
            dataList.add(entity);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < dataList.size(); i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("传统所用时间" + (endTime - startTime));


        dataList.stream().forEach(f -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime1 = System.currentTimeMillis();
        System.out.println("串行所用时间" + (endTime1 - endTime));


        dataList.parallelStream().forEach(f -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime2 = System.currentTimeMillis();
        System.out.println("并行所用时间" + (endTime2 - endTime1));
    }

    public static void test2() {
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        List<Integer> parallelStorage = new ArrayList<>();
//        List<Integer> parallelStorage = Collections.synchronizedList(new ArrayList<>());
        listOfIntegers.parallelStream().forEachOrdered(e -> {
            parallelStorage.add(e);
        });

        System.out.println();
        parallelStorage.stream().forEach(e -> System.out.print(e + " "));

    }
}
