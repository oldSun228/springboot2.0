package com.xch.study.biz.java8.entity;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author fgs
 * @data 2019/1/17 16:48
 */
@Data
public class Student1Entity {
    private String name;

    private List<String> courses;

    public Student1Entity(String name, List<String> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public String removeCourse(String course) {
        boolean removed = courses.remove(courses);

        if (removed) {
            return course;
        } else {
            return null;
        }
    }

    public List<String> getCourses() {
        return Collections.unmodifiableList(courses);
    }

}
