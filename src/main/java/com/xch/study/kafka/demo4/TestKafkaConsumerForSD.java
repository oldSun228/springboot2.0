package com.xch.study.kafka.demo4;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author fgs
 * @data 2019/1/23 17:05
 */
public class TestKafkaConsumerForSD {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.6:9092,192.168.1.5:9092,192.168.1.4:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "CountryCounter");
//        props.put("enable.auto.commit", "false"); //关闭自动提交
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("HelloWorld"));
//        consumer.subscribe(Arrays.asList("HelloWorld"));
        final int minBatchSize = 1;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                insertIntoDb(buffer);
                //自上次调用poll()方法后，提交所订阅的所有topic的partition消费进度
                consumer.commitSync();
                buffer.clear();
            }
        }

    }

    private static void insertIntoDb(List<ConsumerRecord<String, String>> buffer) {
        for (ConsumerRecord<String, String> record : buffer) {
            System.out.printf("offset = %d, value = %s", record.offset(), record.value() + "\n");
        }
    }
}
