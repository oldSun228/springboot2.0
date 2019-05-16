package com.xch.study.kafka.demo4;

import com.alibaba.fastjson.JSONObject;
import com.xch.study.kafka.demo4.exception.ProduceCallback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author fgs
 * @data 2019/1/23 17:27
 */
public class TestKafkapRroduce {

    public static final String brokerList = "192.168.1.6:9092,192.168.1.5:9092,192.168.1.4:9092";
    public static final String topic = "HelloWorld";


    public static void main(String[] args) {
        Properties kafkaProduceProperties = new Properties();
        kafkaProduceProperties.put("bootstrap.servers", brokerList);
        //该参数可以是任意的字符串，服务器会用它来识别消息的来橱，还可以用在日志和配额指标里。
        kafkaProduceProperties.put("client.id", "HelloWorld-producer-client-id-1");
        kafkaProduceProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProduceProperties.put("value.serializer", "com.xch.study.kafka.demo4.entity.serializer.JsonSerialize");
        Producer<String, JSONObject> producer = null;
        try {
            producer = new KafkaProducer<>(kafkaProduceProperties);
            JSONObject jsonObject;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (true) {
                jsonObject = new JSONObject();
                String curTime = sdf.format(new Date());
                jsonObject.put("time", curTime);
                System.out.println(">>>>>>>>>>>>>>" + curTime);
                String key = "key";
                ProducerRecord<String, JSONObject> producerRecord = new ProducerRecord<>(topic, key, jsonObject);
                //异步发送消息
                Future<RecordMetadata> future = producer.send(producerRecord, new ProduceCallback() {
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        System.out.print(metadata.offset() + " ");
                        System.out.print(metadata.topic() + " ");
                        System.out.println(metadata.partition());
                    }
                });
                TimeUnit.SECONDS.sleep(600000);
//                TimeUnit.SECONDS.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

}
