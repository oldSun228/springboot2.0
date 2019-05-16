package com.xch.study.kafka.demo4.entity.serializer;

import com.xch.study.kafka.demo4.entity.CustomerEntity;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author fgs
 * @data 2019/1/25 9:50
 */
public class CustomerSerializer implements Serializer<CustomerEntity> {
    /**
     * 用来配置当前类
     * @param configs
     * @param isKey
     */
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    /**
     * 用来执行序列化
     * @param topic
     * @param data
     * @return
     */
    public byte[] serialize(String topic, CustomerEntity data) {
        if (data == null) {
            return null;
        }
        byte[] name, address;
        try {
            if (data.getName() != null) {
                name = data.getName().getBytes("UTF-8");
            } else {
                name = new byte[0];
            }
            if (data.getAddress() != null) {
                address = data.getAddress().getBytes("UTF-8");
            } else {
                address = new byte[0];
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + name.length + address.length);
            buffer.putInt(name.length);
            buffer.put(name);
            buffer.putInt(address.length);
            buffer.put(address);
            return buffer.array();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 用来关闭当前序列化器。一般情况下这个方法都是个空方法，如果实现了此方法，必须确保此方法的幂等性，因为这个方法很可能会被KafkaProducer调用多次
     */
    public void close() {
    }
}
