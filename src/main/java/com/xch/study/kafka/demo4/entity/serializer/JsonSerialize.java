package com.xch.study.kafka.demo4.entity.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author fgs
 * @data 2019/4/11 19:44
 */
public class JsonSerialize implements Serializer<JSONObject> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, JSONObject data) {
        return JSON.toJSONBytes(data);
    }

    @Override
    public void close() {
    }
}
