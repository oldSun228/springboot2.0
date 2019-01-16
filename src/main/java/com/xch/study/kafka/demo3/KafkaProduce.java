package com.xch.study.kafka.demo3;

import com.alibaba.fastjson.JSONObject;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.*;

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
        JSONObject object = new JSONObject();
        object.put("name", "姓名" + UUID.randomUUID());
        object.put("age", "年龄" + UUID.randomUUID());
        object.put("time", new Date().getTime());
        map.put(0, JSONObject.toJSONString(object));
        object = new JSONObject();
        object.put("class", "班级" + UUID.randomUUID());
        object.put("people", "人员" + UUID.randomUUID());
        object.put("time", new Date().getTime());
        map.put(1, JSONObject.toJSONString(object));
        object = new JSONObject();
        object.put("comply", "公司" + UUID.randomUUID());
        object.put("area", "地址" + UUID.randomUUID());
        object.put("time", new Date().getTime());
        map.put(2, JSONObject.toJSONString(object));
        object = new JSONObject();
        object.put("bc", "班次" + UUID.randomUUID());
        object.put("age", "年龄" + UUID.randomUUID());
        object.put("time", new Date().getTime());
        map.put(3, JSONObject.toJSONString(object));
        object = new JSONObject();
        object.put("hb", "航班" + UUID.randomUUID());
        object.put("fj", "飞机" + UUID.randomUUID());
        object.put("time", new Date().getTime());
        map.put(4, JSONObject.toJSONString(object));
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
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【生产消息：" + map.get(random.nextInt(5)));
            producer.send(new KeyedMessage<Integer, String>(topic, map.get(random.nextInt(5))));
            try {
                //发送消息的时间间隔
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        // 使用kafka集群中创建好的主题 test
        new KafkaProduce("T1").start();
    }

}
