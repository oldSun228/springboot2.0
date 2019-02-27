package com.xch.study.kafka.demo4.exception;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author fgs
 * @data 2019/1/24 12:07
 */
public class ProduceCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            System.out.println(e.getMessage());
        }
    }
}
