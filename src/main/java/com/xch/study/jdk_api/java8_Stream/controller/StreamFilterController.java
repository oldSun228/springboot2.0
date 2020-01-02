package com.xch.study.jdk_api.java8_Stream.controller;

import com.xch.study.jdk_api.java8_Stream.entity.Student;
import com.xch.study.jdk_api.java8_Stream.utils.InitDataUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author fgs
 * @Date 2020/1/2 11:53
 * @Version 1.0
 * @Description 筛选
 **/
public class StreamFilterController {

    /**
     * @return void
     * @Author fgs
     * @Description 更具字段条件筛选
     * @Date 11:54 2020/1/2
     * @Param [args]
     **/
    public static void main(String[] args) {
        List<Student> streamStudents = testFilter(InitDataUtil.getInitData());
        streamStudents.forEach(System.out::println);
    }

    /**
     * 集合的筛选
     *
     * @param students
     * @return
     */
    private static List<Student> testFilter(List<Student> students) {
        return students.stream().filter(s -> "浙江".equals(s.getAddress()) && "李现".equals(s.getName())).collect(Collectors.toList());
    }
}
