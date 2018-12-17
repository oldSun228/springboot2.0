package com.xch.study.es.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author fgs
 * @data 2018/12/11 17:00
 */
@Configuration
public class ElasticSearchConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${elasticsearch.node1.esHost}")
    private String firstHost;
    @Value("${elasticsearch.node1.esPort}")
    private String firstPort;
    @Value("${elasticsearch.node2.esHost}")
    private String secondHost;
    @Value("${elasticsearch.node2.esPort}")
    private String secondPort;
    @Value("${elasticsearch.esClusterName}")
    private String clusterName;

}
