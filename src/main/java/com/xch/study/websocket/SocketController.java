package com.xch.study.websocket;

import com.alibaba.fastjson.JSONObject;
import com.xch.study.utils.IpUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author fgs
 * @data 2018/11/14 19:59
 */
@Controller
@RequestMapping(value = "/socket")
public class SocketController {

    /**
     * view:(跳转到JSP界面).
     *
     * @param map
     * @return
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String view(Map<String, Object> map) {
        map.put("name", "SpringBoot");
        map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "index";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ApiOperation(value = "消息发送", notes = "消息发送", httpMethod = "POST", response = Map.class)
    public void sendMessage(HttpServletRequest request) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ip", IpUtils.getIpAddr(request));
            jsonObject.put("content", "内容");
            WebSocket.sendInfo(JSONObject.toJSONString(jsonObject));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
