package com.xch.study.redis.controller;

import com.xch.study.redis.entity.UsereEntity;
import com.xch.study.redis.service.RedisCommonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RedisCommonService redisCommonService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/setSetValue", method = RequestMethod.POST)
    @ApiOperation(value = "出入string类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public void setSetValue() {
        Set<String> set1 = new HashSet<>();
        set1.add("set1");
        set1.add("set2");
        set1.add("set3");
        redisTemplate.opsForSet().add("set1", set1);
        Set<String> resultSet = redisTemplate.opsForSet().members("set1");
        Iterator<String> iterator = resultSet.iterator();
//        Iterator<String> iterator = set1.iterator();
        while (iterator.hasNext()){
            String entity=iterator.next();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>:" + entity);
        }

    }

    @RequestMapping(value = "/setStringValue", method = RequestMethod.POST)
    @ApiOperation(value = "出入string类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public void setStringValue() {
        String str = "测试";
        redisCommonService.saveObject("s1", str);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + redisCommonService.queryObject("s1"));
    }

    @RequestMapping(value = "/setListValue", method = RequestMethod.POST)
    @ApiOperation(value = "出入List类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public void setListValue() {
        List<UsereEntity> ls = new ArrayList<>();
        UsereEntity entity;
        for (int i = 0; i < 10; i++) {
            entity = new UsereEntity();
            entity.setName("name" + i);
            entity.setSex("sex" + i);
            ls.add(entity);
        }
        redisCommonService.delete("l1");

        redisCommonService.rightPushAllElementsToList("l1", ls);
        List<UsereEntity> list = redisCommonService.queryElementsFromList("l1");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(">>>>>>>>>>>>>>>>>>>" + list.get(i).getName());
        }
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
        redisCommonService.delete("m1");

        redisCommonService.saveMap("m1", map);
        System.out.println(">>>>>>>>>>>>>>>>>>>" + redisCommonService.queryMap("m1", "key1"));
    }

    @RequestMapping(value = "/setMapListValue", method = RequestMethod.POST)
    @ApiOperation(value = "出入List类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public void setMapListValue() {
        Map<String, List<UsereEntity>> map = new HashMap<>();
        UsereEntity entity;
        List<UsereEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entity = new UsereEntity();
            entity.setName("name" + i);
            entity.setSex("sex" + i);
            list.add(entity);
        }
        map.put("mapList", list);
        redisCommonService.delete("ml1");

        redisCommonService.saveMap("ml1", map);
        System.out.println(">>>>>>>>>>>>>>>>>>>" + redisCommonService.queryMap("ml1", "mapList"));
    }
}
