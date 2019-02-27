package com.xch.study.biz.qualifier.util;

import com.xch.study.biz.qualifier.entity.TrafficCode;
import com.xch.study.biz.qualifier.service.TrafficMode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fgs
 * @data 2019/2/27 11:20
 */

/**
 * 交通方式工厂类
 */
@Component
public class TrafficModeFactory implements ApplicationContextAware {

    private static Map<TrafficCode, TrafficMode> trafficBeanMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, TrafficMode> map = applicationContext.getBeansOfType(TrafficMode.class);
        trafficBeanMap = new HashMap<>();
        map.forEach((key, value) -> trafficBeanMap.put(value.getCode(), value));
    }

    public static <T extends TrafficMode> T getTrafficMode(TrafficCode code) {
        return (T) trafficBeanMap.get(code);
    }

}

