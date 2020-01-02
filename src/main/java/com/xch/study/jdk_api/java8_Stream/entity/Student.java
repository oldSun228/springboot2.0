package com.xch.study.jdk_api.java8_Stream.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Author fgs
 * @Date 2020/1/2 11:51
 * @Version 1.0
 * @Description
 **/
@Data
public class Student {

    private int id;

    private String name;

    private int age;

    private String address;
    private BigDecimal money;

    public Student() {
    }

    public Student(int id, String name, int age, String address, BigDecimal money) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.money = money;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, address);
    }
}
