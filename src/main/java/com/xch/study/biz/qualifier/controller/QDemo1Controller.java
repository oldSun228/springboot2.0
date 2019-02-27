package com.xch.study.biz.qualifier.controller;

import com.xch.study.biz.qualifier.entity.TrafficCode;
import com.xch.study.biz.qualifier.service.QDemo1Service;
import com.xch.study.biz.qualifier.service.TrafficMode;
import com.xch.study.biz.qualifier.util.TrafficModeFactory;
import io.swagger.annotations.ApiOperation;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author fgs
 * @data 2018/12/29 9:59
 */
@RestController
@RequestMapping(value = "/qdemo1")
public class QDemo1Controller {

    @Autowired
    @Qualifier("qdemo_1Service")
    QDemo1Service qdemo1Service;

    @RequestMapping(value = "/queryCount", method = RequestMethod.POST)
    @ApiOperation(value = "", notes = "", httpMethod = "POST", response = Map.class)
    public Integer queryCount() {
        long startTime = System.currentTimeMillis();
        Integer count = qdemo1Service.queryCount();
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + count);
        return count;
    }

    @RequestMapping(value = "/queryTestCount", method = RequestMethod.POST)
    @ApiOperation(value = "", notes = "", httpMethod = "POST", response = Map.class)
    public Integer queryTestCount() {
        TrafficMode mode = TrafficModeFactory.getTrafficMode(TrafficCode.BUS);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + mode.getFee().intValue());
        Assert.assertEquals(mode.getFee().intValue(), 10000);

//        mode = TrafficModeFactory.getTrafficMode(TrafficCode.TRAIN);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + mode.getFee().intValue());
//        Assert.assertEquals(mode.getFee().intValue(), 9000);
        return mode.getFee().intValue();
    }

}
