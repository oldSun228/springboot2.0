package com.xch.study.biz.excelmanage.service;

import com.xch.study.biz.excelmanage.entity.ExcelDataEntity;

import java.util.List;

/**
 * @author fgs
 * @data 2019/4/26 17:58
 */
public interface ApplicationDataService {


    /**
     * @return java.util.List<com.xch.study.biz.excelmanage.entity.ExcelDataEntity>
     * @Author fgs
     * @Description 查询数据
     * @Date 10:13 2020/1/7
     * @Param []
     **/
    List<ExcelDataEntity> queryData();

    /**
     * @return void
     * @Author fgs
     * @Description 数据导入
     * @Date 10:32 2020/1/7
     * @Param [readResult]
     **/
    void saveData(List<ExcelDataEntity> readResult);
}
