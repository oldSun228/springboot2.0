package com.xch.study.jdk_api.java8_Stream.controller;

import com.alibaba.fastjson.JSONObject;
import com.xch.study.jdk_api.java8_Stream.entity.Student;
import com.xch.study.jdk_api.java8_Stream.utils.InitDataUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author fgs
 * @Date 2020/1/2 11:53
 * @Version 1.0
 * @Description 转换
 **/
public class StreamMapController {

    /**
     * @return void
     * @Author fgs
     * @Description 更具字段条件筛选
     * @Date 11:54 2020/1/2
     * @Param [args]
     **/
    public static void main(String[] args) {
        testMap(InitDataUtil.getInitData());
    }

    /**
     * 集合转换
     *
     * @param students
     * @return
     */
    private static void testMap(List<Student> students) {
        //在地址前面加上部分信息，只获取地址输出
//        List<String> addresses = students.stream().map(s -> "住址:" + s.getAddress()).collect(Collectors.toList());
//        addresses.forEach(a -> System.out.println(a));

        Map<Integer, Student> mapData = students.stream().collect(Collectors.toMap(Student::getId, a -> a, (k1, k2) -> k1));

        Iterator<Map.Entry<Integer, Student>> entries = mapData.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Student> next = entries.next();
            System.out.println(JSONObject.toJSONString(next));
        }
    }
}
