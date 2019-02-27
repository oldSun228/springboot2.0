package com.xch.study.kafka.demo4.entity;

import lombok.Data;

/**
 * @author fgs
 * @data 2019/1/25 9:42
 */
@Data
public class CustomerEntity {
    private int customerId;
    private String customerName;

    public CustomerEntity(int id, String name) {
        this.customerId = id;
        this.customerName = name;
    }

    public int getId() {
        return customerId;
    }

    public String getName() {
        return customerName;
    }
}
