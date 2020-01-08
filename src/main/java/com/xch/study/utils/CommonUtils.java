package com.xch.study.utils;

import java.util.UUID;

/**
 * @Author fgs
 * @Date 2020/1/7 14:16
 * @Version 1.0
 * @Description
 **/
public class CommonUtils {

    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
