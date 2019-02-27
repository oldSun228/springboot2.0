package com.xch.study.biz.qualifier.service.impl;

/**
 * @author fgs
 * @data 2019/2/27 11:23
 */

import com.xch.study.biz.qualifier.entity.TrafficCode;
import com.xch.study.biz.qualifier.service.TrafficMode;
import org.springframework.stereotype.Component;

/**
 * 火车方式
 */
@Component
public class TrainMode implements TrafficMode {

    @Override
    public TrafficCode getCode() {
        return TrafficCode.TRAIN;
    }

    @Override
    public Integer getFee() {
        return 9000;
    }

}
