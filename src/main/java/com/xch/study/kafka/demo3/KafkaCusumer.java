package com.xch.study.kafka.demo3;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author fgs
 * @data 2019/1/16 19:57
 */
public class KafkaCusumer extends Thread {
    private String topic;//主题

    private long i;

    public KafkaCusumer(String topic) {
        super();
        this.topic = topic;
    }

    //创建消费者
    private ConsumerConnector createConsumer() {
        Properties properties = new Properties();
//        //zkInfo
//        properties.put("zk.connect", "127.0.0.1:2181");
//        //必须要使用别的组名称， 如果生产者和消费者都在同一组，则不能访问同一组内的topic数据
//        properties.put("group.id", "group1");
        // zookeeper 配置
        properties.put("zookeeper.connect", "127.0.0.1:2181");

        // group 代表一个消费组
        properties.put("group.id", "lingroup");

        // zk连接超时
        properties.put("zookeeper.session.timeout.ms", "4000");
        properties.put("zookeeper.sync.time.ms", "200");
        properties.put("rebalance.max.retries", "5");
        properties.put("rebalance.backoff.ms", "1200");


        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "smallest");
        // 序列化类
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
    }

    @Override
    public void run() {
        //创建消费者
        ConsumerConnector consumer = createConsumer();
        //主题数map
        Map<String, Integer> topicCountMap = new HashMap<>();
        // 一次从topic主题中获取一个数据
        topicCountMap.put(topic, 1);
        //创建一个获取消息的消息流
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);
        // 获取每次接收topic主题到的这个数据
        KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();

        try {
            //循环打印
            while (iterator.hasNext()) {
                String message = new String(iterator.next().message());
                i++;
                System.out.println("接收到  " + i + " 条消息: " + message);
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        // 使用kafka集群中创建好的主题 test
        new KafkaCusumer("T1").start();
    }
}
