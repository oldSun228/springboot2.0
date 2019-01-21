package com.xch.study.biz.java8.controller;

import com.xch.study.biz.java8.entity.Student1Entity;

import java.util.ArrayList;

/**
 * @author fgs
 * @data 2019/1/17 16:47
 */
public class UnmodifiableList {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("001");
//        list.add("002");
//        StudentEntity s = new StudentEntity("Tom", list);
//        List<String> anotherList = s.getCourses();
//        anotherList.add("999");
//        anotherList.stream().forEach(o -> System.out.println(o));


        ArrayList<String> list = new ArrayList<>();
        list.add("001");
        list.add("002");
        Student1Entity s = new Student1Entity("Tom", list);
        s.addCourse("999");

        System.out.println("Tom's course.length = " + s.getCourses().size());
        s.getCourses().stream().forEach(o -> System.out.println(o));
    }


}
