package com.xch.study.websocket;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fgs
 * @data 2018/12/25 13:27
 */
@Data
public class MessageEntity implements Serializable {

    private static final long serialVersionUID = -6451812593150428369L;

    private String sourse;// 信息来源
    private String messageType;// 消息类型
    private String msgContent;// 消息内容
    private String target;// 发送目的地
    private String infoSourceIP;// 信息来源ip
    private String createtime;// 消息保存时间
    private String otherContent;// 其他信息
}