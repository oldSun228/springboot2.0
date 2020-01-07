package com.xch.study.biz.excelmanage.service.impl;


import com.xch.study.biz.excelmanage.entity.ExcelDataEntity;
import com.xch.study.biz.excelmanage.mapper.ApplicationDataMapper;
import com.xch.study.biz.excelmanage.service.ApplicationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fgs
 * @data 2019/4/26 18:01
 */
@Service("applicationDataService")
public class ApplicationDataServiceImpl implements ApplicationDataService {


    @Autowired
    private ApplicationDataMapper applicationDataMapper;

    /**
     * @return java.util.List<com.xch.study.biz.excelmanage.entity.ExcelDataEntity>
     * @Author fgs
     * @Description 查询数据
     * @Date 10:13 2020/1/7
     * @Param []
     **/
    @Override
    public List<ExcelDataEntity> queryData() {
        return applicationDataMapper.queryData();
    }

    /**
     * @Author fgs
     * @Description 数据导入
     * @Date 10:32 2020/1/7
     * @Param [readResult]
     * @return void
     **/
    @Override
    public void saveData(List<ExcelDataEntity> readResult) {

    }
}
