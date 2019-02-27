package com.xch.study.biz.qualifier.service.impl;

import com.xch.study.biz.qualifier.mapper.QDemo1Mapper;
import com.xch.study.biz.qualifier.service.QDemo1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fgs
 * @data 2018/12/29 10:02
 */
@Service("qdemo_2Service")
public class QDemo_2ServiceImpl implements QDemo1Service {
    @Autowired
    QDemo1Mapper qdemo1Mapper;

    @Override
    public Integer queryCount() {
        return qdemo1Mapper.queryUserCount();
    }
}
