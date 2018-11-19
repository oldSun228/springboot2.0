package com.xch.study.es.controller;

import io.swagger.annotations.ApiOperation;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author fgs
 * @data 2018/11/14 19:59
 */
@RestController
@RequestMapping(value = "/test")
public class ElasticSearchCreateController {

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    @ApiOperation(value = "出入string类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public void test1() {
        //1.判定索引是否存在
        boolean flag = ESUtil.isExists("fgs_2018_11_14");
        System.out.println("isExists:" + flag);
//        //2.创建索引,分片,副本
        flag = ESUtil.createIndex("index1", 3, 0);
        System.out.println("createIndex:" + flag);
        //3.设置Mapping
        try {
            XContentBuilder builder = jsonBuilder().startObject().startObject("properties")
                    .startObject("id").field("type", "long").endObject()
                    .startObject("title").field("type", "text").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").field("boost", 2).endObject()
                    .startObject("content").field("type", "text").field("analyzer", "ik_max_word").field("search_analyzer", "ik_max_word").endObject()
                    .startObject("postdate").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()
                    .startObject("url").field("type", "keyword").endObject().endObject().endObject();
            System.out.println(builder.string());
            ESUtil.setMapping("index1", "blog", builder.string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
