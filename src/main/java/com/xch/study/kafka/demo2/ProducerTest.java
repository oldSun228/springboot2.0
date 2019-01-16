package com.xch.study.kafka.demo2;

/**
 * @author fgs
 * @data 2019/1/14 17:04
 */

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class ProducerTest {
    private final Producer<String, String> producer;
    public final static String TOPIC = "linlin";

    private ProducerTest() {
        Properties props = new Properties();
        // 此处配置的是kafka的端口
        props.put("metadata.broker.list", "127.0.0.1:9092");
        props.put("zk.connect", "127.0.0.1:2181");

        // 配置value的序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        // 配置key的序列化类
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");

        props.put("request.required.acks", "-1");

        producer = new Producer<>(new ProducerConfig(props));
    }

    void produce() {
        int messageNo = 10;
        final int COUNT = 20;

        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "hello kafka message " + key;
            producer.send(new KeyedMessage<>(TOPIC, key, data));
            System.out.println(data);
            messageNo++;
        }
    }

    public static void main(String[] args) {
        new ProducerTest().produce();
        //System.out.println("你好 KafkaProducer");
    }
}
