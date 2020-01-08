package com.xch.study.biz.excelmanage.mapper;


import com.xch.study.biz.excelmanage.entity.*;

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

    void mergeIntoTable1Data(Table1Entity table1Entity);

    void mergeIntoTable2Data(Table2Entity table2Entity);

    void mergeIntoTable3Data(Table3Entity table3Entity);

    void insertIntoTable4Data(Table4Entity table4Entity);

    void insertIntoTable5Data(Table5Entity table5Entity);

    void mergeIntoTable6Data(Table6Entity table6Entity);

    List<Table1Entity> checkNameIsNotExist(String name);
}
