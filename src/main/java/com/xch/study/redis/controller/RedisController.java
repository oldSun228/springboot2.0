package com.xch.study.redis.controller;

import com.xch.study.redis.entity.UsereEntity;
import com.xch.study.utils.RedisUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author fgs
 * @date 2018/5/5 15:23
 * @description :
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    private RedisUtils redisUtils;


    @RequestMapping(value = "/setStringValue", method = RequestMethod.POST)
    @ApiOperation(value = "出入String类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public void setStringValue() {
        UsereEntity entity= new UsereEntity();
        entity.setName("name");
        entity.setSex("sex");
        redisUtils.set("string1", entity);
        UsereEntity entity1= (UsereEntity) redisUtils.get("string1");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + entity1.getName());
//        redisUtils.set("string1", "测试中国");
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + redisUtils.get("string1"));


//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        valueOperations.getOperations().delete("string1");
//        valueOperations.set("string1", "测试中国");
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + valueOperations.get("string1"));
    }

    @RequestMapping(value = "/setSetValue", method = RequestMethod.POST)
    @ApiOperation(value = "出入Set类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public void setSetValue() {
//        redisUtils.sSet("set1",100, "setValue1");
//        redisUtils.sSet("set1", "setValue2");
//        redisUtils.sSet("set1", "setValue3");

        Set<Object> resultSet = redisUtils.sGet("set1");
        Iterator<Object> iterator = resultSet.iterator();
        while (iterator.hasNext()) {
            Object set = iterator.next();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + set.toString());
        }


//        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
//        setOperations.getOperations().delete("set1");
//        setOperations.add("set1", "setValue1");
//        setOperations.add("set1", "setValue2");
//        setOperations.add("set1", "setValue3");
//        Set<String> resultSet = setOperations.members("set1");
//        Iterator<String> iterator = resultSet.iterator();
//        while (iterator.hasNext()) {
//            String set = iterator.next();
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>:" + set);
//        }
    }

    @RequestMapping(value = "/setListValue", method = RequestMethod.POST)
    @ApiOperation(value = "出入List类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public List<UsereEntity> setListValue() {
        redisUtils.del("list1");
        List<UsereEntity> ls = new ArrayList<>();
        UsereEntity entity;
        for (int i = 0; i < 10; i++) {
            entity = new UsereEntity();
            entity.setName("name" + i);
            entity.setSex("sex" + i);
            ls.add(entity);
        }
        redisUtils.lSet("list1", ls);

        List<UsereEntity> list = redisUtils.lGet("list1", 1, 10);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(">>>>>>>>>>>>>>>>>>>" + list.get(i).getName());
        }

//        ListOperations<String, UsereEntity> listOperations = redisTemplate.opsForList();
//        List<UsereEntity> ls = new ArrayList<>();
//        UsereEntity entity;
//        for (int i = 0; i < 10000; i++) {
//            entity = new UsereEntity();
//            entity.setName("name" + i);
//            entity.setSex("sex" + i);
//            ls.add(entity);
//        }
//        listOperations.getOperations().delete("list1");
//
//        listOperations.rightPushAll("l1", ls);
//        List<UsereEntity> list = redisCommonService.queryElementsFromList("l1");
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(">>>>>>>>>>>>>>>>>>>" + list.get(i).getName());
//        }
        return null;
    }

    @RequestMapping(value = "/setMapValue", method = RequestMethod.POST)
    @ApiOperation(value = "出入List类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public void setMapValue() {
        Map<String, UsereEntity> map = new HashMap<>();
        UsereEntity entity;
        for (int i = 0; i < 10; i++) {
            entity = new UsereEntity();
            entity.setName("name" + i);
            entity.setSex("sex" + i);
            map.put("key" + i, entity);
        }
        redisUtils.hmset("map1", map);

        Map<String, UsereEntity> resultMap = redisUtils.hmget("map1");
        Iterator<Map.Entry<String, UsereEntity>> iterator = resultMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, UsereEntity> val = iterator.next();
            System.out.println(val.getKey() + "" + val.getValue().getName());
        }


//        Map<String, UsereEntity> map = new HashMap<>();
//        UsereEntity entity;
//        for (int i = 0; i < 10; i++) {
//            entity = new UsereEntity();
//            entity.setName("name" + i);
//            entity.setSex("sex" + i);
//            map.put("key" + i, entity);
//        }
//        redisTemplate.opsForHash().getOperations().delete("m1");
//
//        redisTemplate.opsForHash().putAll("m1", map);
//        Map<String, UsereEntity> resultMap = redisTemplate.opsForHash().entries("m1");
//        Iterator<Map.Entry<String, UsereEntity>> iterator = resultMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, UsereEntity> val = iterator.next();
//            System.out.println(val.getKey() + "" + val.getValue().getName());
//        }
//
//        List<String> reslutList = redisTemplate.opsForHash().values("m1");
//        Set<String> resultSet = redisTemplate.opsForHash().keys("m1");
//        UsereEntity resultEntity = (UsereEntity) redisTemplate.opsForHash().get("m1", "key1");
//
//
//        System.out.println("resultMapListMap:" + resultMap);
//        System.out.println("resultMap:" + reslutList);
//        System.out.println("resultMapSet:" + resultSet);
//        System.out.println("value:" + resultEntity.getName());
    }

}
