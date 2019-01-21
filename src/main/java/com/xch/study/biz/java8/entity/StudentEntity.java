package com.xch.study.biz.java8.entity;

import lombok.Data;

import java.util.List;

/**
 * @author fgs
 * @data 2019/1/17 16:48
 */
@Data
public class StudentEntity {
    private String name;

    private List<String> courses;

    public StudentEntity(String name, List<String> courses) {
        this.name = name;
        this.courses = courses;
    }

}
