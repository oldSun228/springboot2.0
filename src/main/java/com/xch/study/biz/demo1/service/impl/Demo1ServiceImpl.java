package com.xch.study.biz.demo1.service.impl;

import com.xch.study.biz.demo1.mapper.Demo1Mapper;
import com.xch.study.biz.demo1.service.Demo1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author fgs
 * @data 2018/12/29 10:02
 */
@Service("demo1Service")
public class Demo1ServiceImpl implements Demo1Service {

    @Autowired
    Demo1Mapper demo1Mapper;

    @Override
    public List<Map<String, Object>> querySinglTableCountForThread() {
        List<Future<Map<String, Object>>> result = new ArrayList<>();
        Integer threadSize = 10;
        ExecutorService threadPool = Executors.newFixedThreadPool(threadSize);


        return null;
    }

    @Override
    public Map<String, Object> querySinglTableCount() {
        Map<String, Object> result = new HashMap<>();
        result.put(new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(System.currentTimeMillis()), demo1Mapper.querySinglTableCount());
        return result;
    }
}
