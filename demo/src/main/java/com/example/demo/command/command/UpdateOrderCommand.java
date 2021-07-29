package com.example.demo.command.command;

import com.example.demo.query.entity.Address;
import com.example.demo.query.entity.Product;
import lombok.Getter;

@Getter
public class UpdateOrderCommand extends BaseCommand<String> {

    private final int quantity;
    private final double price;
    private final String status;
    private final Product product;
    private final Address address;
    private final String updatedAt;

    public UpdateOrderCommand(String id, int quantity, double price, String status, Product product,
                              Address address, String updatedAt) {
        super(id);
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.product = product;
        this.address = address;
        this.updatedAt = updatedAt;
    }
}
