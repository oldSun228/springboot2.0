package com.xch.study.biz.demo1.service.impl;

import com.xch.study.biz.demo1.mapper.Demo1Mapper;
import com.xch.study.biz.demo1.service.Demo1Service;
import com.xch.study.biz.threadUtils.Demo1ForThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

/**
 * @author fgs
 * @data 2018/12/29 10:02
 */
@Service("demo1Service")
public class Demo1ServiceImpl implements Demo1Service {

    @Autowired
    Demo1Mapper demo1Mapper;

    @Autowired
    Demo1Service demo1Service;

    @Override
    public List<Map<String, Object>> querySinglTableCountForThread() {
        List<Future<Map<String, Object>>> result = new ArrayList<>();
        List<Map<String, Object>> listResult = new ArrayList<>();
        Integer threadSize = 10;
        ExecutorService threadPool = Executors.newFixedThreadPool(threadSize);
        for (int i = 0; i < 300; i++) {
            Future<Map<String, Object>> future = threadPool.submit(new Demo1ForThread(demo1Service));
            result.add(future);
        }
        Map<String, Object> singl = null;
        for (Future<Map<String, Object>> o : result) {
            try {
                singl = o.get(50, TimeUnit.MILLISECONDS);
                listResult.add(singl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
        return listResult;
    }

    @Override
    public List<Map<String, Object>> querySinglTableCounts() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            result.add(getData());
        }
        return result;
    }

    @Override
    public Map<String, Object> querySinglTableCount() {
        return getData();
    }


    public Map<String, Object> getData() {
        Map<String, Object> result = null;
        try {
            Thread.sleep(20);
            result = new HashMap<>();
            result.put(String.valueOf(UUID.randomUUID()), demo1Mapper.querySinglTableCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
