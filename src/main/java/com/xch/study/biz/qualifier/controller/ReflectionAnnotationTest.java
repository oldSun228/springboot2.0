package com.xch.study.biz.qualifier.controller;

import com.xch.study.biz.qualifier.entity.TrafficCode;
import com.xch.study.biz.qualifier.service.TrafficMode;
import com.xch.study.biz.qualifier.util.TrafficModeFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author fgs
 * @data 2019/2/27 11:12
 */
public class ReflectionAnnotationTest {

    @Test
    public void testGetTrafficMode() {
        TrafficMode mode = TrafficModeFactory.getTrafficMode(TrafficCode.BUS);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + mode.getFee().intValue());
        Assert.assertEquals(mode.getFee().intValue(), 10000);

//        mode = TrafficModeFactory.getTrafficMode(TrafficCode.TRAIN);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + mode.getFee().intValue());
//        Assert.assertEquals(mode.getFee().intValue(), 9000);
    }


}

