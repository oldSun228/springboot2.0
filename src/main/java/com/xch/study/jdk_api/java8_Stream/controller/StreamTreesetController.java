package com.xch.study.jdk_api.java8_Stream.controller;

import com.xch.study.jdk_api.java8_Stream.entity.Student;
import com.xch.study.jdk_api.java8_Stream.utils.InitDataUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @Author fgs
 * @Date 2020/1/2 11:53
 * @Version 1.0
 * @Description 筛选
 **/
public class StreamTreesetController {

    /**
     * @return void
     * @Author fgs
     * @Description 更具字段条件筛选
     * @Date 11:54 2020/1/2
     * @Param [args]
     **/
    public static void main(String[] args) {
        /**去重*/
//        List<Student> streamStudents = testTreeset(InitDataUtil.getInitData());
        List<Student> streamStudents = testTreesetSort(InitDataUtil.getInitData());
        streamStudents.forEach(System.out::println);

        /**去重排序*/

    }

    /**
     * @Author fgs
     * @Description  去重
     * @Date 13:59 2020/1/2
     * @Param [students]
     * @return java.util.List<com.xch.study.jdk_api.java8_Stream.entity.Student>
     **/
    private static List<Student> testTreeset(List<Student> students) {
        // 根据id去重
        return students.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Student::getId))), ArrayList::new));
    }


    /**
     * @Author fgs
     * @Description 排序
     * @Date 13:59 2020/1/2
     * @Param [students]
     * @return java.util.List<com.xch.study.jdk_api.java8_Stream.entity.Student>
     **/
    private static List<Student> testTreesetSort(List<Student> students) {
        return students.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
    }
}
