package com.example.demo.command.service;

import com.example.demo.command.command.CreateOrderCommand;
import com.example.demo.command.command.UpdateOrderCommand;
import com.example.demo.command.dto.CreateOrderRequest;
import com.example.demo.command.dto.UpdateOrderRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderCommandService {

    private final CommandGateway commandGateway;

    public OrderCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createOrder(CreateOrderRequest createOrderRequest) {
        String createdAt = LocalDateTime.now().toString();
        String updatedAt = createdAt;

        return commandGateway.send(new CreateOrderCommand(UUID.randomUUID().toString(),
                createOrderRequest.getCustomerId(),
                createOrderRequest.getQuantity(), createOrderRequest.getPrice(), createOrderRequest.getStatus(),
                createOrderRequest.getProduct(), createOrderRequest.getAddress(), createdAt, updatedAt));
    }

    public CompletableFuture<String> updateOrder(UpdateOrderRequest updateOrderRequest) {
        String updatedAt = LocalDateTime.now().toString();

        return commandGateway.send(new UpdateOrderCommand(updateOrderRequest.get_id(),
                updateOrderRequest.getQuantity(), updateOrderRequest.getPrice(), updateOrderRequest.getStatus(),
                updateOrderRequest.getProduct(), updateOrderRequest.getAddress(), updatedAt));
    }
}
