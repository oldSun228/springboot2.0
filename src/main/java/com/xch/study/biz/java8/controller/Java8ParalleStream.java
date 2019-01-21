package com.xch.study.biz.java8.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author fgs
 * @data 2019/1/17 17:20
 */
public class Java8ParalleStream {
    private static List<String> list1 = new ArrayList<>();
    private static List<String> list2 = new ArrayList<>();


    public static void main(String[] args) {
        initData();
        forEach();
//        forEachOrdered();
    }

    private static void initData() {
        IntStream.range(1, 10000).forEachOrdered(o -> list1.add(String.valueOf(o)));
        System.out.println("list1的长度" + list1.size());
    }

    /**
     * 遍历数据无顺序输出
     */
    public static void forEach() {
        list1.parallelStream().forEach(System.out::println);
    }

    /**
     * 原有数据顺序输出
     */
    public static void forEachOrdered() {
        list1.parallelStream().forEachOrdered(list2::add);

        System.out.println(list2.size());
    }

}
