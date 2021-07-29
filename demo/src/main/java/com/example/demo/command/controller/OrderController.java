package com.example.demo.command.controller;

import com.example.demo.command.dto.CreateOrderRequest;
import com.example.demo.command.dto.UpdateOrderRequest;
import com.example.demo.command.service.OrderCommandService;
import com.example.demo.query.entity.Order;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/order-command")
@Slf4j
public class OrderController {

    private final OrderCommandService orderCommandService;

    public OrderController(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @PostMapping(value = "/create-order")
    public ResponseEntity<String> createOrder(@Validated @RequestBody CreateOrderRequest request) {
        try {
            CompletableFuture<String> response = orderCommandService.createOrder(request);

            return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update-order")
    public ResponseEntity<String> updateOrder(@Validated @RequestBody UpdateOrderRequest request) {
        try {
            CompletableFuture<String> response = orderCommandService.updateOrder(request);
            JSONObject json = new JSONObject();
            json.put("", "");
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
