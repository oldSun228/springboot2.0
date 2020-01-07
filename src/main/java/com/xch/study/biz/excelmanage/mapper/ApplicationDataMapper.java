package com.xch.study.biz.excelmanage.mapper;


import com.xch.study.biz.excelmanage.entity.ExcelDataEntity;

import java.util.List;

/**
 * @author fgs
 * @data 2019/4/26 18:01
 */
public interface ApplicationDataMapper {


    /**
     * @return java.util.List<com.xch.study.biz.excelmanage.entity.ExcelDataEntity>
     * @Author fgs
     * @Description 查询数据
     * @Date 10:15 2020/1/7
     * @Param []
     **/
    List<ExcelDataEntity> queryData();
}
