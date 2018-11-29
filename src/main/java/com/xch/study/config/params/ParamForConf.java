package com.xch.study.config.params;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 此内部类就是把yml的配置数据，进行读取，创建JedisConnectionFactory和JedisPool，以供外部类初始化缓存管理器使用
 * 不了解的同学可以去看@ConfigurationProperties和@Value的作用
 *
 * @author fgs
 * @data 2018/11/29 9:19
 */
@Component
@Data
public class ParamForConf {
    /**
     * Logger
     */
    private static final Logger lg = LoggerFactory.getLogger(ParamForConf.class);

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;
}
