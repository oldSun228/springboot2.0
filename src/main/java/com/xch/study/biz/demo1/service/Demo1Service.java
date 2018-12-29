package com.xch.study.biz.demo1.service;

import java.util.List;
import java.util.Map;

/**
 * @author fgs
 * @data 2018/12/29 10:02
 */
public interface Demo1Service {
    List<Map<String, Object>> querySinglTableCountForThread();

    List<Map<String, Object>> querySinglTableCounts();

    Map<String, Object> querySinglTableCount();
}
