package com.xch.study.kafka.demo2;

/**
 * @author fgs
 * @data 2019/1/14 17:06
 */

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * zk启动：sh /opt/zookeeper-3.4.11/bin/zkServer.sh start &
 * kafka启动：sh /opt/kafka_2.12-1.1.0/bin/kafka-server-start.sh /opt/kafka_2.12-1.1.0/config/server.properties &
 */
public class ConsumerTest {

    private final ConsumerConnector consumer;
    public final static String TOPIC = "linlin";

    private ConsumerTest() {
        Properties props = new Properties();
        // zookeeper 配置
        props.put("zookeeper.connect", "127.0.0.1:2181");

        // group 代表一个消费组
        props.put("group.id", "lingroup");

        // zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("rebalance.max.retries", "5");
        props.put("rebalance.backoff.ms", "1200");


        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        // 序列化类
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        ConsumerConfig config = new ConsumerConfig(props);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

    void consume() {
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(TOPIC, new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get(TOPIC).get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext())
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + it.next().message() + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    public static void main(String[] args) {
        new ConsumerTest().consume();
    }
}