package com.xch.study.es.controller;

import com.alibaba.fastjson.JSONObject;
import com.xch.study.config.params.ParamForConf;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author fgs
 * @data 2018/11/14 19:59
 */
@RestController
@RequestMapping(value = "/elasticSearch")
public class ElasticSearchCreateController {

    @Resource
    private ParamForConf paramForConf;

    @RequestMapping(value = "/createIndex", method = RequestMethod.POST)
    @ApiOperation(value = "创建索引", notes = "创建索引", httpMethod = "POST", response = Map.class)
    public void createIndex() {
        //1.判定索引是否存在
        boolean flag = ESUtil.isExists(paramForConf.getIndex());
        if (flag) {
            ESUtil.deleteIndex(paramForConf.getIndex());
        }
        System.out.println("isExists:" + flag);
        //2.创建索引,分片,副本
        flag = ESUtil.createIndex(paramForConf.getIndex(), 1, 0);
        System.out.println("createIndex:" + flag);
        //3.设置Mapping
        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .field("dynamic", "strict")
                    .startObject("properties")
                    .startObject("full_all")
                    .field("type", "text")
                    .field("fielddata", true)
                    .endObject()
                    .startObject("createtime")
                    .field("type", "keyword")
                    .field("copy_to", "full_all")
                    .endObject()
                    .startObject("username")
                    .field("type", "keyword")
                    .field("copy_to", "full_all")
                    .endObject()
                    .startObject("account")
                    .field("type", "keyword")
                    .field("copy_to", "full_all")
                    .endObject()
                    .startObject("sex")
                    .field("type", "keyword")
                    .field("copy_to", "full_all")
                    .endObject()
                    .endObject()
                    .endObject();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + builder.string());
            ESUtil.setMapping(paramForConf.getIndex(), paramForConf.getType(), builder.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/deleteIndex", method = RequestMethod.POST)
    @ApiOperation(value = "删除索引", notes = "删除索引", httpMethod = "POST", response = Map.class)
    public boolean deleteIndex() {
        return ESUtil.deleteIndex(paramForConf.getIndex());
    }


    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    @ApiOperation(value = "新增数据保存", notes = "新增数据保存", httpMethod = "POST", response = Map.class)
    public void saveData() {
        JSONObject json;
        for (int i = 0; i < 20; i++) {
            json = new JSONObject();
            json.put("createtime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
            json.put("username", "username" + i);
            json.put("account", "account" + i);
            json.put("sex", "sex" + i);
            ESUtil.saveData(paramForConf.getIndex(), paramForConf.getType(), json);
        }
    }


    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    @ApiOperation(value = "新增数据保存", notes = "新增数据保存", httpMethod = "POST", response = Map.class)
    public void deleteData() {
        DeleteResponse deleteResponse = ESUtil.deleteData(paramForConf.getIndex(), paramForConf.getType(), "Fs0HvGcBBTyoXmDsXBJ6");
        System.out.println("deleteResponse索引名称:" + deleteResponse.getIndex() + "\n deleteResponse类型:" + deleteResponse.getType()
                + "\n deleteResponse文档ID:" + deleteResponse.getId() + "\n当前实例deleteResponse状态:" + deleteResponse.status());

    }

    @RequestMapping(value = "/updataData", method = RequestMethod.POST)
    @ApiOperation(value = "修改数据保存", notes = "修改数据保存", httpMethod = "POST", response = Map.class)
    public void updataData(@RequestBody String id) {
        UpdateResponse updateResponse = ESUtil.updataData(paramForConf.getIndex(), paramForConf.getType(), id);
        System.out.println("updateResponse索引名称:" + updateResponse.getIndex() + "\n updateResponse类型:" + updateResponse.getType()
                + "\n updateResponse文档ID:" + updateResponse.getId() + "\n当前实例updateResponse状态:" + updateResponse.status());

    }


    @RequestMapping(value = "/queryData", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询", notes = "分页查询", httpMethod = "POST", response = Map.class)
    public void queryData() {
        SearchResponse response = ESUtil.queryData(paramForConf.getIndex(), paramForConf.getType());
        //response.getHits()是所有命中记录  相较于sql select * from accounts.person limit 100;
        SearchHits searchHits = response.getHits();
        for (SearchHit hit : searchHits.getHits()) {
            System.out.println(hit.getSourceAsString() + ">>>>>>>>>>" + hit.getId());
        }
    }

    @RequestMapping(value = "/queryDataById", method = RequestMethod.POST)
    @ApiOperation(value = "详情查询", notes = "详情查询", httpMethod = "POST", response = Map.class)
    public String queryDataById(@RequestBody String id) {
        GetResponse getResponse = ESUtil.queryDataById(paramForConf.getIndex(), paramForConf.getType(), id);
        return getResponse.getSourceAsString();
    }

    @RequestMapping(value = "/queryDataForCondition", method = RequestMethod.POST)
    @ApiOperation(value = "条件查询", notes = "条件查询", httpMethod = "POST", response = Map.class)
    public void queryDataForCondition() {
        SearchRequestBuilder sv = ESUtil.queryDataForCondition(paramForConf.getIndex(), paramForConf.getType());
        SearchResponse response = sv.get();
        SearchHits searchHits = response.getHits();
        for (SearchHit hit : searchHits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }

    @RequestMapping(value = "/queryDataForConditions", method = RequestMethod.POST)
    @ApiOperation(value = "条件查询", notes = "条件查询", httpMethod = "POST", response = Map.class)
    public void queryDataForConditions() {
        SearchRequestBuilder sv = ESUtil.queryDataForConditions(paramForConf.getIndex(), paramForConf.getType());
        SearchResponse response = sv.get();
        SearchHits searchHits = response.getHits();
        for (SearchHit hit : searchHits.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }

}
