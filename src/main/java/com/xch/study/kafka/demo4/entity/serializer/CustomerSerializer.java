package com.xch.study.kafka.demo4.entity.serializer;

import com.xch.study.kafka.demo4.entity.CustomerEntity;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @author fgs
 * @data 2019/1/25 9:50
 */
public class CustomerSerializer implements Serializer<CustomerEntity> {
    @Override
    public void configure(Map<String, ?> configsMap, boolean isKey) {
        //不做任何配置
    }

    @Override
    /**
     * Customer对象被序列化成：
     * 表示customerIDflb4字节整数
     * 表^customerName长度的4字节整数(如果customerName为空，则长度为0)
     * 表示customerName的N个字节
     */
    public byte[] serialize(String topic, CustomerEntity data) {
        try {
            byte[] serializedName;
            int stringSize;
            if (data == null) {
                return null;
            } else {
                if (data.getName() != null) {
                    serializedName = data.getName().getBytes("UTF-8");
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
            buffer.putInt(data.getId());
            buffer.putInt(stringSize);
            buffer.put(serializedName);
            return buffer.array();
        } catch (Exception e) {
            throw new SerializationException("Error when serializing Customer to byte[] " + e);
        }
    }

    @Override
    public void close() {
        //不需要关闭任何东西
    }
}
