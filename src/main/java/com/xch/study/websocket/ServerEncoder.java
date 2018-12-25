package com.xch.study.websocket;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.index.mapper.MapperException;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author fgs
 * @data 2018/12/25 13:27
 */
public class ServerEncoder implements Encoder.Text<MessageEntity> {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(EndpointConfig arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public String encode(MessageEntity messagepojo) {
        try {
            return JSONObject.toJSONString(messagepojo);
        } catch (MapperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

}

