package com.xch.study.redis.controller;

import com.xch.study.redis.entity.UsereEntity;
import com.xch.study.utils.RedisUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
    public UsereEntity setStringValue() {
        UsereEntity entity = new UsereEntity();
        entity.setName("name");
        entity.setSex("sex");
        redisUtils.set("string1", entity);
        UsereEntity entity1 = (UsereEntity) redisUtils.get("string1");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + entity1.getName());
//        redisUtils.set("string1", "测试中国");
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + redisUtils.get("string1"));


//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//        valueOperations.getOperations().delete("string1");
//        valueOperations.set("string1", "测试中国");
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + valueOperations.get("string1"));
        return entity1;
    }

    @RequestMapping(value = "/setSetValue", method = RequestMethod.POST)
    @ApiOperation(value = "出入Set类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public void setSetValue() {
        redisUtils.sSet("set1", 100, "setValue1");
        redisUtils.sSet("set1", "setValue2");
        redisUtils.sSet("set1", "setValue3");

        Set<Object> resultSet = redisUtils.sGet("set1");
        Iterator<Object> iterator = resultSet.iterator();
        while (iterator.hasNext()) {
            Object set = iterator.next();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + set.toString());
        }
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

        return list;
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
    }


    @RequestMapping(value = "/session", method = RequestMethod.POST)
    @ApiOperation(value = "出入List类型的数值", notes = "redis操作", httpMethod = "POST", response = Map.class)
    public ResponseEntity<?> session(HttpSession session) {
        if (session.isNew()) {
            System.out.println("Successfully creates a session ，the id of session ：" + session.getId());
            session.setAttribute("key", "hello");
        } else {
            System.out.println("session already exists in the server, the id of session ：" + session.getId());
            System.out.println(session.getAttribute("key").toString());
        }
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}
