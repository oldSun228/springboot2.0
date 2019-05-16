package com.xch.study.kafka.demo4.entity.serializer;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author fgs
 * @data 2019/4/11 19:45
 */
public class JsonDeserialize implements Deserializer<JSONObject> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public JSONObject deserialize(String topic, byte[] data) {
        return JSONObject.parseObject(new String(data));
    }

    @Override
    public void close() {
    }
}
