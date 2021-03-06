package com.xch.study.biz.excelmanage.controller;

import com.xch.study.biz.excelmanage.entity.ExcelDataEntity;
import com.xch.study.biz.excelmanage.service.ApplicationDataService;
import com.xch.study.biz.excelmanage.utils.ExcelReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author fgs
 * @Date 2020/1/6 18:00
 * @Version 1.0
 * @Description
 **/
@RestController
@RequestMapping("excelManage")
public class ExcelManageController {

    public static void main(String[] args) {
        // 设定Excel文件所在路径
        String excelFileName = "C:\\Users\\86158\\Desktop\\abc.xlsx";
        // 读取Excel文件内容
        List<ExcelDataEntity> readResult = ExcelReader.readExcel(excelFileName);
        Map<String, String> result = new HashMap<>();
        readResult.stream().forEachOrdered(o -> {
            result.put(o.getLi20(), o.getLi20());
        });

        System.out.println(">>>>>>>>>>>" + result.size());
    }

    @Autowired
    private ApplicationDataService applicationDataService;

    /**
     * @return java.util.List<com.xch.study.biz.excelmanage.entity.ExcelDataEntity>
     * @Author fgs
     * @Description 测试连接数据库接口
     * @Date 10:10 2020/1/7
     * @Param []
     **/
    @RequestMapping(value = "queryData", method = RequestMethod.POST)
    public List<ExcelDataEntity> queryData() throws Exception {
        List<ExcelDataEntity> listData = applicationDataService.queryData();
        return listData;
    }


    /**
     * @return void
     * @Author fgs
     * @Description 数据导入
     * @Date 10:32 2020/1/7
     * @Param []
     **/
    @RequestMapping(value = "saveData", method = RequestMethod.POST)
    public void saveData() throws Exception {
        // 设定Excel文件所在路径
        String excelFileName = "C:\\Users\\86158\\Desktop\\abc.xlsx";
        // 读取Excel文件内容
        List<ExcelDataEntity> readResult = ExcelReader.readExcel(excelFileName);
        applicationDataService.saveData(readResult);
    }

}
