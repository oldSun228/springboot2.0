package com.xch.study.websocket;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author fgs
 * @data 2018/11/14 19:59
 */
@Controller
@RequestMapping(value = "/socket")
public class SocketController {

    /**
     * view:(跳转到JSP界面)
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = {"/{name}"}, method = RequestMethod.GET)
    public String view(@PathVariable String name, Model model) {
        //通过Model进行对象数据的传递
        model.addAttribute("username", name);
        return "index";
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    @ApiOperation(value = "消息发送", notes = "消息发送", httpMethod = "POST", response = Map.class)
    public void sendMessage(HttpServletRequest request) {
//        try {
//            MessageEntity bean = new MessageEntity();
//            bean.setInfoSourceIP(IpUtils.getIpAddr(request));
//            WebSocket.sendInfo(bean);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
