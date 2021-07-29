package com.example.demo.command.aggreagate;

import com.example.demo.command.command.CreateOrderCommand;
import com.example.demo.command.command.UpdateOrderCommand;
import com.example.demo.common.event.OrderCreatedEvent;
import com.example.demo.common.event.OrderUpdatedEvent;
import com.example.demo.query.entity.Address;
import com.example.demo.query.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class OrderAggregate {

    @AggregateIdentifier
    private String _id;
    private String customerId;
    private int quantity;
    private double price;
    private String status;
    private Product product;
    private Address address;
    private String createdAt;
    private String updatedAt;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        log.info("CreateOrderCommand received.");

        AggregateLifecycle.apply(new OrderCreatedEvent(command.getId(), command.getCustomerId(),
                command.getQuantity(), command.getPrice(), "ORDERED",
                command.getProduct(), command.getAddress(), command.getCreatedAt(), command.getUpdatedAt()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        log.info("An OrderCreatedEvent occurred.");
        this._id = event.getId();
        this.customerId = event.getCustomerId();
        this.quantity = event.getQuantity();
        this.price = event.getPrice();
        this.status = "CREATED";
        this.product = event.getProduct();
        this.address = event.getAddress();
        this.createdAt = event.getCreatedAt();
        this.updatedAt = event.getUpdatedAt();
    }

    @CommandHandler
    public void on(UpdateOrderCommand command) {
        log.info("UpdateOrderCommand received.");

        AggregateLifecycle.apply(new OrderUpdatedEvent(command.getId(), command.getQuantity(), command.getPrice(),
                "UPDATED", command.getProduct(), command.getAddress(), command.getUpdatedAt()));
    }

    @EventSourcingHandler
    public void on(OrderUpdatedEvent event) {
        log.info("An OrderUpdatedEvent occurred.");

        this.quantity = event.getQuantity();
        this.price = event.getPrice();
        this.status = event.getStatus();
        this.product = event.getProduct();
        this.address = event.getAddress();
        this.updatedAt = event.getUpdatedAt();
    }


}
