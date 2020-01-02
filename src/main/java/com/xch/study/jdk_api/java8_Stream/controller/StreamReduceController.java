package com.xch.study.jdk_api.java8_Stream.controller;

import com.xch.study.jdk_api.java8_Stream.entity.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author fgs
 * @Date 2020/1/2 11:53
 * @Version 1.0
 * @Description 计算 总金额
 **/
public class StreamReduceController {

    /**
     * @return void
     * @Author fgs
     * @Description 更具字段条件筛选
     * @Date 11:54 2020/1/2
     * @Param [args]
     **/
    public static void main(String[] args) {
        Student s1 = new Student(1, "肖战", 15, "浙江", new BigDecimal(5));
        Student s2 = new Student(2, "王一博", 15, "湖北", new BigDecimal(15));
        Student s3 = new Student(3, "杨紫", 17, "北京", new BigDecimal(25));
        Student s4 = new Student(4, "李现", 17, "浙江", new BigDecimal(35));
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        testMap(students);
    }

    /**
     * 集合转换
     *
     * @param students
     * @return
     */
    private static void testMap(List<Student> students) {
        //计算 总金额
        BigDecimal totalMoney = students.stream().map(Student::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.err.println("totalMoney:" + totalMoney);
    }
}
