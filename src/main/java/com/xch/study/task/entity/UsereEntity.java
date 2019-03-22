package com.xch.study.task.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fgs
 * @data ${DATA} 16:42
 */
@Data
public class UsereEntity implements Serializable {

    private static final long serialVersionUID = 5702207261403657120L;
    private String name;
    private String sex;

}
