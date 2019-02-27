package com.xch.study.biz.qualifier.service;

import com.xch.study.biz.qualifier.entity.TrafficCode;

/**
 * @author fgs
 * @data 2019/2/27 11:22
 */
public interface TrafficMode {
    /**
     * 查询交通方式编码
     * @return 编码
     */
    TrafficCode getCode();

    /**
     * 查询交通方式的费用，单位：分
     * @return 费用
     */
    Integer getFee();

}
