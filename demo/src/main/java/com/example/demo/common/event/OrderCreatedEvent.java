package com.example.demo.common.event;

import com.example.demo.query.entity.Address;
import com.example.demo.query.entity.Product;
import lombok.Getter;

@Getter
public class OrderCreatedEvent extends BaseEvent<String> {
    private final String customerId;
    private final int quantity;
    private final double price;
    private final String status;
    private final Product product;
    private final Address address;
    private final String createdAt;
    private final String updatedAt;

    public OrderCreatedEvent(String id, String customerId, int quantity, double price, String status,
                             Product product, Address address, String createdAt, String updatedAt) {
        super(id);
        this.customerId = customerId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.product = product;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
