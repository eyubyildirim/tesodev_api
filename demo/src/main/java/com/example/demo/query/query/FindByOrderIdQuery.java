package com.example.demo.query.query;

import lombok.Data;

@Data
public class FindByOrderIdQuery {

    private String orderId;

    public FindByOrderIdQuery(String orderId) {
        this.orderId = orderId;
    }
}
