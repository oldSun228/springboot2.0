package com.xch.study.task.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xch.study.websocket.WebSocket;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Redis公共类
 *
 * @author wei.sun
 * @version 1.0
 * @since 2016-1-20
 */
@Service
public class TaskServiceImpl {

    @Scheduled(cron = "0/5 * * * * *")
    public void queryData() {
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>.");
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//
//            JSONObject object = new JSONObject();
//            object.put("textMessage", sd.format(new Date()));
//            object.put("messageType", "4");
//            object.put("fromusername", "");
//            object.put("tousername", "");
//            object.put("zrs", WebSocket.onlineNumber);
//            WebSocket.sendMessageAll(JSONObject.toJSONString(object), "");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
