package com.xch.study.biz.demo1.controller;

import com.xch.study.biz.demo1.service.Demo1Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author fgs
 * @data 2018/12/29 9:59
 */
@RestController
@RequestMapping(value = "/demo1")
public class Demo1Controller {

    @Autowired
    Demo1Service demo1Service;

    @RequestMapping(value = "/querySinglTableCountForThread", method = RequestMethod.POST)
    @ApiOperation(value = "", notes = "", httpMethod = "POST", response = Map.class)
    public List<Map<String, Object>> querySinglTableCountForThread() {
        List<Map<String, Object>> result = demo1Service.querySinglTableCountForThread();
        return result;
    }

    @RequestMapping(value = "/querySinglTableCount", method = RequestMethod.POST)
    @ApiOperation(value = "", notes = "", httpMethod = "POST", response = Map.class)
    public Map<String, Object> querySinglTableCount() {
        Map<String, Object> result = demo1Service.querySinglTableCount();
        return result;
    }

}
