package com.xch.study.kafka.demo3;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;

/**
 * @author fgs
 * @data 2019/1/16 20:24
 */
public class KafkaSpoutMain {
    // 主题与zk端口
    public static final String TOPIC = "ARF";
    public static final String ZKINFO = "127.0.0.1:2181";

    public static void main(String[] args) {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        //创建zk主机
        ZkHosts zkHosts = new ZkHosts(ZKINFO);
        //创建spout
        SpoutConfig spoutConfig = new SpoutConfig(zkHosts, TOPIC, "", "KafkaSpout");
        //整合kafkaSpout
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        // 设置storm数据源为kafka整合storm的kafkaSpout
        topologyBuilder.setSpout("KafkaSpout", kafkaSpout, 1);
        //数据流向，流向dataBolt进行处理
        topologyBuilder.setBolt("dataBolt", new DataBolt(), 1).shuffleGrouping("KafkaSpout");

        Config config = new Config();
        config.setNumWorkers(1);

        if (args.length > 0) {
            try {
                StormSubmitter.submitTopology(args[0], config, topologyBuilder.createTopology());
            } catch (Exception e) {
            }
        } else {
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("getData", config, topologyBuilder.createTopology());
        }
    }
}
