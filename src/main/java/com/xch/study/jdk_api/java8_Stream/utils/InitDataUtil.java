package com.xch.study.jdk_api.java8_Stream.utils;

import com.xch.study.jdk_api.java8_Stream.entity.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author fgs
 * @Date 2020/1/2 13:10
 * @Version 1.0
 * @Description
 **/
public class InitDataUtil {

    public static List<Student> getInitData() {
        List<Student> resultData = new ArrayList<>();
        Student s1 = new Student(1, "肖战", 15, "浙江", new BigDecimal(5));
        Student s3 = new Student(3, "杨紫", 17, "北京", new BigDecimal(25));
        Student s2 = new Student(2, "王一博", 15, "湖北", new BigDecimal(15));
        Student s4 = new Student(4, "李现", 17, "浙江", new BigDecimal(35));
        Student s5 = new Student(4, "李现1", 17, "浙江", new BigDecimal(35));
        resultData.add(s1);
        resultData.add(s2);
        resultData.add(s3);
        resultData.add(s4);
        resultData.add(s5);
        return resultData;
    }
}
