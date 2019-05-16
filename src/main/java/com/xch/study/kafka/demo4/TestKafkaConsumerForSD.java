package com.xch.study.kafka.demo4;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

/**
 * @author fgs
 * @data 2019/1/23 17:05
 * 手动的提交消费进度   优先考虑
 */
public class TestKafkaConsumerForSD {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.6:9092,192.168.1.5:9092,192.168.1.4:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.xch.study.kafka.demo4.entity.serializer.JsonDeserialize");
        props.put("group.id", "CountryCounter1");
//        props.put("enable.auto.commit", "false"); //关闭自动提交
        KafkaConsumer<String, JSONObject> consumer = new KafkaConsumer<>(props);
        //订阅主题
        consumer.subscribe(Collections.singletonList("HelloWorld"));
//        consumer.subscribe(Arrays.asList("HelloWorld"));
        final int minBatchSize = 1;
        List<ConsumerRecord<String, JSONObject>> buffer = new ArrayList<>();
        try {
            while (true) {
                ConsumerRecords<String, JSONObject> records = null;
                try {
                    records = consumer.poll(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (ConsumerRecord<String, JSONObject> record : records) {
                    buffer.add(record);
                }
                if (buffer.size() >= minBatchSize) {
                    insertIntoDb(buffer);
                    //自上次调用poll()方法后，提交所订阅的所有topic的partition消费进度  异步提交的方法
                    consumer.commitAsync(new OffsetCommitCallback() {
                        @Override
                        public void onComplete(Map<TopicPartition, OffsetAndMetadata> offset, Exception e) {
                            if (e != null) {
                                System.out.println(">>>>>offset = " + offset + ", errorMessage = " + e.getMessage());
                            }
                        }
                    });
                    buffer.clear();
                }
            }
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>记录错误日志");
        } finally {
            try {
                buffer.clear();
                //自上次调用poll()方法后，提交所订阅的所有topic的partition消费进度  同步提交的方法
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }

    }

    private static void insertIntoDb(List<ConsumerRecord<String, JSONObject>> buffer) {
        for (ConsumerRecord<String, JSONObject> record : buffer) {
            System.out.printf("offset = %d, value = %s", record.offset(), record.value() + "\n");
        }
    }
}
