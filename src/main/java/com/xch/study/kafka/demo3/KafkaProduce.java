package com.xch.study.kafka.demo3;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * @author fgs
 * @data 2019/1/16 19:55
 */
public class KafkaProduce extends Thread {
    // 主题
    private String topic;
    // 数据源容器
    private static final Map<Integer, String> map = new HashMap<>();
    final Random random = new Random();

    static {
        map.put(0, "java");
        map.put(1, "php");
        map.put(2, "groovy");
        map.put(3, "python");
        map.put(4, "ruby");
    }

    public KafkaProduce(String topic) {
        super();
        this.topic = topic;
    }

    //创建生产者
    private Producer createProducer() {
        Properties properties = new Properties();
        //zookeeper单节点
        properties.put("zk.connect", "127.0.0.1:2181");
        //kafka单节点
        properties.put("metadata.broker.list", "127.0.0.1:9092");
        // 配置value的序列化类
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        // 配置key的序列化类
        properties.put("key.serializer.class", "kafka.serializer.StringEncoder");

        properties.put("request.required.acks", "-1");
        return new Producer<Integer, String>(new ProducerConfig(properties));
    }

    @Override
    public void run() {
        //创建生产者
        Producer producer = createProducer();
        //循环发送消息到kafka
        while (true) {
            producer.send(new KeyedMessage<Integer, String>(topic, map.get(random.nextInt(5))));
            try {
                //发送消息的时间间隔
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        // 使用kafka集群中创建好的主题 test
        new KafkaProduce("ARF").start();
    }

}
