package com.xch.study.biz.excelmanage.service.impl;

import com.xch.study.biz.excelmanage.entity.*;
import com.xch.study.biz.excelmanage.mapper.ApplicationDataMapper;
import com.xch.study.biz.excelmanage.service.ApplicationDataService;
import com.xch.study.utils.AutoDateFormateUtils;
import com.xch.study.utils.CommonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
     * @return void
     * @Author fgs
     * @Description 数据导入
     * @Date 10:32 2020/1/7
     * @Param [readResult]
     **/
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public void saveData(List<ExcelDataEntity> readResult) {
        /**停车场经营企业信息*/
        Table1Entity table1Entity = null;
        /**停车场基本信息*/
        Table2Entity table2Entity = null;
        /**停车场详细信息*/
        Table3Entity table3Entity = null;
        /**停车场场库详细信息*/
        Table4Entity table4Entity = null;
        /**停车场进出口信息*/
        Table5Entity table5Entity = null;
        /**停车场数据状态*/
        Table6Entity table6Entity = null;

        if (CollectionUtils.isNotEmpty(readResult)) {
            for (int i = 0; i < readResult.size(); i++) {
                //企业名称不为空的话
                if (StringUtils.isNotEmpty(readResult.get(i).getLi20())) {
                    table1Entity = new Table1Entity();
                    //企业ID
                    String qyId = CommonUtils.get32UUID();
                    table1Entity.setLi1(qyId);
                    //营业执照
                    table1Entity.setLi2(readResult.get(i).getLi21());
                    //企业名称
                    table1Entity.setLi3(readResult.get(i).getLi20());
                    //注册地址
                    table1Entity.setLi4(readResult.get(i).getLi27());
                    //注册区属
                    table1Entity.setLi5(readResult.get(i).getLi29());
                    //注册地址_区县ID
                    table1Entity.setLi6(getAreaId(readResult.get(i).getLi29()));
                    //注册街道
                    table1Entity.setLi7(readResult.get(i).getLi28());
                    //企业经济性质
                    table1Entity.setLi8(readResult.get(i).getLi24());
                    //业户类型
                    table1Entity.setLi9(readResult.get(i).getLi23());
                    //法定代表人
                    table1Entity.setLi10(readResult.get(i).getLi25());
                    //法定代表人电话
                    table1Entity.setLi11(readResult.get(i).getLi26());
                    //企业邮编
                    if (StringUtils.isNotEmpty(readResult.get(i).getLi22())) {
                        table1Entity.setLi12(Integer.parseInt(readResult.get(i).getLi22()));
                    }
                    //是否首次备案（0：首次，1:非首次）
                    table1Entity.setLi13(0);
                    //数据状态（默认值0，0正常，1删除）
                    table1Entity.setLi14(0);
                    //创建日期
                    table1Entity.setLi15(AutoDateFormateUtils.getCurTime("yyyy-MM-dd HH:mm:ss"));
                    //创建人
                    table1Entity.setLi16("博坤");
                    /**先查看数据库是否存在 不存在插入 */
                    applicationDataMapper.mergeIntoTable1Data(table1Entity);
                    /****************************************************************************************/
                    /**插入停车场基本信息表*/
                    table2Entity = new Table2Entity();
                    String tccjcxxId = CommonUtils.get32UUID();
                    //                    停车场基础信息ID
                    table2Entity.setLi1(tccjcxxId);
                    //                    企业ID
                    table2Entity.setLi2(qyId);
                    //                    备案证号
                    table2Entity.setLi3(readResult.get(i).getLi2());
                    //                    备案证期限
                    table2Entity.setLi4(readResult.get(i).getLi38());
                    //                    ID
                    table2Entity.setLi5(readResult.get(i).getLi30());
                    //                    是否告知承诺:1是0否
                    table2Entity.setLi6(0);
                    //                    区属
                    table2Entity.setLi7(readResult.get(i).getLi1());
                    //                    数据状态（默认值0，0正常，1删除）
                    table2Entity.setLi8(0);
                    //                    创建日期
                    table2Entity.setLi9(AutoDateFormateUtils.getCurTime("yyyy-MM-dd HH:mm:ss"));
                    //                    创建人
                    table2Entity.setLi10("博坤");
                    //                    场库类型  1其他停车场，2临时停车场
                    if ("临时停车场".equals(readResult.get(i).getLi39())) {
                        table2Entity.setLi11("2");
                    } else {
                        table2Entity.setLi11("1");
                    }

                    /**先查看数据库是否存在 不存在插入 */
                    applicationDataMapper.mergeIntoTable2Data(table2Entity);
                    /****************************************************************************************/
                    /**插入停车场基本信息表*/
                    table3Entity = new Table3Entity();
                    //                    停车场详细信息ID
                    String tccxxxxId = CommonUtils.get32UUID();
                    table3Entity.setLi1(tccxxxxId);
                    //                    停车场基础信息ID
                    table3Entity.setLi2(tccjcxxId);
                    //                    场库名称
                    table3Entity.setLi3(readResult.get(i).getLi3());
                    //                    从业人员数
                    table3Entity.setLi4(readResult.get(i).getLi7());
                    //                    场库地址
                    table3Entity.setLi5(readResult.get(i).getLi4());
                    //                    场库区属
                    table3Entity.setLi6(readResult.get(i).getLi16());
                    //                    场库街道
                    table3Entity.setLi7(readResult.get(i).getLi17());
                    //                    总面积
                    table3Entity.setLi8(Integer.parseInt(readResult.get(i).getLi37()));
                    //                    总泊位
                    table3Entity.setLi9(Integer.parseInt(readResult.get(i).getLi33()));
                    //                    换乘泊位
                    table3Entity.setLi10(Integer.parseInt(readResult.get(i).getLi34()));
                    //                    充电泊位
                    table3Entity.setLi11(Integer.parseInt(readResult.get(i).getLi35()));
                    //                    无障碍泊位
                    table3Entity.setLi12(Integer.parseInt(readResult.get(i).getLi36()));
                    //                    场库产权性质
                    table3Entity.setLi13(readResult.get(i).getLi13());
                    //                    租赁有效期
                    table3Entity.setLi14(readResult.get(i).getLi15());
                    //                    产权方名称
                    table3Entity.setLi15(readResult.get(i).getLi14());
                    //                    负责人
                    table3Entity.setLi16(readResult.get(i).getLi5());
                    //                    负责人电话
                    table3Entity.setLi17(readResult.get(i).getLi6());
                    //                    收费供应商
                    table3Entity.setLi18(readResult.get(i).getLi19());
                    //                    环线位置
                    table3Entity.setLi19(readResult.get(i).getLi8());
                    //                    经营性质(大类)
                    table3Entity.setLi20("公共停车场");
                    //                    场库类型
                    table3Entity.setLi21(readResult.get(i).getLi39());
                    //                    场库邮编
                    table3Entity.setLi22(readResult.get(i).getLi18());
                    //                    服务电话
                    table3Entity.setLi23(readResult.get(i).getLi11());
                    //                    投诉电话
                    table3Entity.setLi24(readResult.get(i).getLi12());
                    //                    数据状态（默认值0，0正常，1删除）
                    table3Entity.setLi25(0);
                    //                    创建日期
                    table3Entity.setLi26(AutoDateFormateUtils.getCurTime("yyyy-MM-dd HH:mm:ss"));
                    //                    创建人
                    table3Entity.setLi27("博坤");
                    //                    区县ID
                    table3Entity.setLi28(getAreaId(readResult.get(i).getLi16()));
                    /**先查看数据库是否存在 不存在插入 */
                    applicationDataMapper.mergeIntoTable3Data(table3Entity);
                    /****************************************************************************************/
                    /**插入停车场基本信息表*/
                    table4Entity = new Table4Entity();
                    //                    停车场场库信息ID
                    String tccckxxId = CommonUtils.get32UUID();
                    table4Entity.setLi1(tccckxxId);
                    //                    停车场基础信息ID
                    table4Entity.setLi2(tccjcxxId);
                    //                    库类型
                    table4Entity.setLi3(readResult.get(i).getLi40());
                    //                    库级别
                    table4Entity.setLi4(readResult.get(i).getLi47());
                    //                    库等别
                    table4Entity.setLi5(readResult.get(i).getLi48());
                    //                    库面积
                    table4Entity.setLi6(Integer.parseInt(readResult.get(i).getLi42()));
                    //                    库泊位
                    table4Entity.setLi7(Integer.parseInt(readResult.get(i).getLi43()));
                    //                    大车泊位
                    table4Entity.setLi8(Integer.parseInt(readResult.get(i).getLi44()));
                    //                    小车泊位
                    table4Entity.setLi9(Integer.parseInt(readResult.get(i).getLi45()));
                    //                    计费标准，计费类型1-元/小时2-其他
                    Integer val = readResult.get(i).getLi46().split(";").length == 1 ? 1 : 2;
                    table4Entity.setLi10(val);
                    //                    计费标准
                    if (val == 1) {
                        table4Entity.setLi11(null);
                    } else {
                        table4Entity.setLi11(readResult.get(i).getLi46());
                    }
                    //                    计费标准(大型车)
                    table4Entity.setLi12(null);
                    //                    计费标准(特大型车)
                    table4Entity.setLi13(null);
                    //                    计费标准(特种车)
                    table4Entity.setLi14(null);
                    //                    自行式泊位数
                    table4Entity.setLi15(Integer.parseInt(readResult.get(i).getLi49()));
                    //                    子母式泊位
                    table4Entity.setLi16(Integer.parseInt(readResult.get(i).getLi51()));
                    //                    机械式泊位
                    table4Entity.setLi17(Integer.parseInt(readResult.get(i).getLi50()));
                    //                    机械架有效期
                    table4Entity.setLi18(readResult.get(i).getLi52());
                    //                    计费方式
                    table4Entity.setLi19(readResult.get(i).getLi55());
                    //                    对外开放泊位
                    table4Entity.setLi20(Integer.parseInt(readResult.get(i).getLi53()));
                    //                    服务时间备注
                    table4Entity.setLi21(readResult.get(i).getLi10());
                    //                    长包泊位
                    table4Entity.setLi22(readResult.get(i).getLi54());
                    //                    付费方式
                    table4Entity.setLi23(readResult.get(i).getLi56());
                    //                    数据状态（默认值0，0正常，1删除）
                    table4Entity.setLi24(0);
                    //                    创建日期
                    table4Entity.setLi25(AutoDateFormateUtils.getCurTime("yyyy-MM-dd HH:mm:ss"));
                    //                    创建人
                    table4Entity.setLi26("博坤");
                    //                    服务时间 1全年全天2阶段性全天3全年时间4阶段性时段性
                    if ("全年全天".equals(readResult.get(i).getLi9())) {
                        table4Entity.setLi27("1");
                    } else if ("阶段性全天".equals(readResult.get(i).getLi9())) {
                        table4Entity.setLi27("2");
                    } else if ("全年时间".equals(readResult.get(i).getLi9())) {
                        table4Entity.setLi27("3");
                    } else if ("阶段性时段性".equals(readResult.get(i).getLi9())) {
                        table4Entity.setLi27("4");
                    }

                    //                    层数
                    table4Entity.setLi28(readResult.get(i).getLi41());
                    /**先查看数据库是否存在 不存在插入 */
                    applicationDataMapper.insertIntoTable4Data(table4Entity);
                    /****************************************************************************************/
                    /**插入停车场基本信息表*/
                    for (int j = 0; j < 2; j++) {
                        table5Entity = new Table5Entity();
                        //                    停车场进出口ID
                        String tccjckId = CommonUtils.get32UUID();
                        table5Entity.setLi1(tccjckId);
                        //                    停车场基础信息ID
                        table5Entity.setLi2(tccjcxxId);
                        if (j == 0) {
                            //                    进出口地址
                            table5Entity.setLi3(readResult.get(j).getLi31());
                            //                    进出类型
                            table5Entity.setLi4(1);
                        } else {
                            //                    进出口地址
                            table5Entity.setLi3(readResult.get(j).getLi32());
                            //                    进出类型
                            table5Entity.setLi4(2);
                        }

                        //                    数据状态（默认值0，0正常，1删除）
                        table5Entity.setLi5(0);
                        //                    创建日期
                        table5Entity.setLi6(AutoDateFormateUtils.getCurTime("yyyy-MM-dd HH:mm:ss"));
                        //                    创建人
                        table5Entity.setLi7("博坤");
                        /**先查看数据库是否存在 不存在插入 */
                        applicationDataMapper.insertIntoTable5Data(table5Entity);
                    }
                    /****************************************************************************************/
                    table6Entity = new Table6Entity();
                    //                    停车场数据状态主键ID
                    String tccsjztId = CommonUtils.get32UUID();
                    table6Entity.setLi1(tccsjztId);
                    //                    停车场基础信息ID
                    table6Entity.setLi2(tccjcxxId);
                    //                    申请数据状态状态（0：新办，1：场库更新，2：企业更新，3：注销，4：补正，5：换证 6:承诺未遵守撤销）
                    table6Entity.setLi3(1);
                    //                    区属
                    table6Entity.setLi4(readResult.get(i).getLi1());
                    //                    备案证号
                    table6Entity.setLi5(readResult.get(i).getLi2());
                    //                    备案期限
                    table6Entity.setLi6(readResult.get(i).getLi38());
                    //                    数据状态（默认值0，0正常，1删除）
                    table6Entity.setLi7(0);
                    //                    创建日期
                    table6Entity.setLi8(AutoDateFormateUtils.getCurTime("yyyy-MM-dd HH:mm:ss"));
                    //                    创建人
                    table6Entity.setLi9("博坤");
                    /**先查看数据库是否存在 不存在插入 */
                    applicationDataMapper.mergeIntoTable6Data(table6Entity);
                }
            }
        }

        System.out.println(">>>>>>>>END>>>>>>>>");

    }


    public String getAreaId(String name) {
        String result = null;
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("市管", "310000");
        dataMap.put("黄浦", "310101");
        dataMap.put("徐汇", "310104");
        dataMap.put("长宁", "310105");
        dataMap.put("静安", "310106");
        dataMap.put("普陀", "310107");
        dataMap.put("虹口", "310109");
        dataMap.put("杨浦", "310110");
        dataMap.put("闵行", "310112");
        dataMap.put("宝山", "310113");
        dataMap.put("嘉定", "310114");
        dataMap.put("浦东", "310115");
        dataMap.put("金山", "310116");
        dataMap.put("松江", "310117");
        dataMap.put("青浦", "310118");
        dataMap.put("奉贤", "310120");
        dataMap.put("崇明", "310151");
        Iterator<Map.Entry<String, String>> iterator = dataMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            if (next.getKey().indexOf(name) > -1) {
                result = next.getValue();
                break;
            }
        }
        return result;
    }

}
