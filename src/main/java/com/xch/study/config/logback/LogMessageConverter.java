package com.xch.study.config.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alibaba.fastjson.JSONObject;

/**
 * @author fgs
 * @data 2018/11/6 14:28
 */
public class LogMessageConverter extends MessageConverter {
    public String convert(ILoggingEvent event) {
        return JSONObject.toJSONString(event.getMDCPropertyMap());
    }
}
