package com.xch.study.es.controller;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fgs
 * @data 2018/11/14 19:44
 */
public class EsController {
    public static void main(String[] args) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        RestClient restClient = RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")).build();
//        System.out.println(123);
//        Map<String, String> params = Collections.emptyMap();
//        for (int i = 1; i <= 10; i++) {
//            System.out.println(" i=" + i + " start ");
//            HashMap<String, Object> context = new HashMap<>();
//            context.put("name", "姓名" + i);
//            context.put("age", i);
//            context.put("createtime", sdf.format(new Date()));
//            String jsonString = JSON.toJSONString(context);
//            HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
//            Response response = restClient.performRequest("POST", "/fgs_2018_11_14/study_first/", params, entity);
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        }

//
        Map<String, String> params = new HashMap<>();
        // params.put("pretty", "true");
        // params.put("q", "pid:33");
        HttpEntity entity = new NStringEntity("{\"query\":{\"bool\":{\"filter\":{\"range\":{\"pid\":{\"gt\":997}}}}}}",ContentType.APPLICATION_JSON);
        // HttpEntity entity1 = new
        // NStringEntity("{\"query\":{\"match\":{\"pid\":\"33\"}}}",
        // ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest("GET", "/posts/_search", params, entity);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
