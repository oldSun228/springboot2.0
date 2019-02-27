package com.xch.study.kafka.demo4;

import com.alibaba.fastjson.JSONObject;
import com.xch.study.kafka.demo4.exception.ProduceCallback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author fgs
 * @data 2019/1/23 17:27
 */
public class TestKafkapRroduce {
    public static void main(String[] args) {
        Properties kafkaProduceProperties = new Properties();
        kafkaProduceProperties.put("bootstrap.servers", "192.168.1.6:9092,192.168.1.5:9092,192.168.1.4:9092");
        kafkaProduceProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProduceProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        kafkaProduceProperties.put("acks", "all");
//        kafkaProduceProperties.put("retries", 0);
//        kafkaProduceProperties.put("batch.size", 16384);
//        kafkaProduceProperties.put("linger.ms", 1);
//        kafkaProduceProperties.put("buffer.memory", 33554432);
        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<>(kafkaProduceProperties);
            JSONObject jsonObject;
            for (int i = 0; i < 10; i++) {
//                Thread.sleep(5000);
                jsonObject = new JSONObject();
                jsonObject.put("name", "name" + i);
                jsonObject.put("age", "age" + i);
                String key = "key";
                String value = JSONObject.toJSONString(jsonObject);
                producer.send(new ProducerRecord<>("HelloWorld", key, value), new ProduceCallback());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

}
