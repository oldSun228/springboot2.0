package com.xch.study.biz.threaddemo1.threadUtils;

import com.xch.study.biz.threaddemo1.service.Demo1Service;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author fgs
 * @data 2018/12/29 14:07
 */
public class Demo1ForThread implements Callable<Map<String, Object>> {

    private Demo1Service demo1Service;

    public Demo1ForThread(Demo1Service demo1Service) {
        this.demo1Service = demo1Service;
    }

    @Override
    public Map<String, Object> call() throws Exception {
        Map<String, Object> result = getData(demo1Service);
        return result;
    }

    private Map<String, Object> getData(Demo1Service demo1Service) {
        Map<String, Object> result = demo1Service.querySinglTableCount();
//        System.out.println("##############################################" + JSONObject.toJSONString(result));
        return result;
    }
}
