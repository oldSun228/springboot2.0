package com.xch.study.kafka.demo3;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author fgs
 * @data 2019/1/16 20:26
 */
public class DataBolt extends BaseRichBolt {

    public int i = 0;
    public Map<String, String> map = new HashMap<>();


    public void execute(Tuple tuple) {
        String string = new String((byte[]) tuple.getValue(0));

        i++;
        String[] datas = string.split(" ");

        System.out.println("【收到消息：" + i + " 条数据】" + string);

        map.put("a", UUID.randomUUID() + "_" + string);
        map.put("b", UUID.randomUUID() + "_" + string);
        map.put("c", UUID.randomUUID() + "_" + string);
        map.put("d", UUID.randomUUID() + "_" + string);

//        jedis.hmset("test", map);
    }

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        // 初始化
    }

    public void declareOutputFields(OutputFieldsDeclarer arg0) {

    }
}
