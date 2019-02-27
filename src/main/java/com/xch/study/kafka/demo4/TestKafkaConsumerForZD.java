package com.xch.study.kafka.demo4;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author fgs
 * @data 2019/1/23 17:02
 */
public class TestKafkaConsumerForZD {
    public static void main(String[] args) {

        Properties props = new Properties();
        //用于初始化时建立链接到kafka集群，以host:port形式，多个以逗号分隔
        props.put("bootstrap.servers", "192.168.1.6:9092,192.168.1.5:9092,192.168.1.4:9092");
        //消费者的组id
        props.put("group.id", "demo5");
        //用于配置是否自动的提交消费进度；
        props.put("enable.auto.commit", "true");
        //用于配置自动提交消费进度的时间；
        props.put("auto.commit.interval.ms", "1000");
        //会话超时时长，客户端需要周期性的发送“心跳”到broker，这样broker端就可以判断消费者的状态，如果消费在会话周期时长内未发送心跳，那么该消费者将被判定为dead，那么它之前所消费的partition将会被重新的分配给其他存活的消费者；
        props.put("session.timeout.ms", "30000");
        //说明了使用何种序列化方式将用户提供的key和vaule值序列化成字节
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //订阅主题列表topic
        consumer.subscribe(Arrays.asList("HelloWorld"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value() + "\n");
        }
    }

}
