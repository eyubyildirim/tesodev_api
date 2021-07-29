package com.example.demo.query.controller;

import com.example.demo.query.entity.Order;
import com.example.demo.query.query.FindAllOrdersQuery;
import com.example.demo.query.query.FindByCustomerIdQuery;
import com.example.demo.query.query.FindByOrderIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/order-query")
public class ManageOrderController {

    private final QueryGateway queryGateway;

    public ManageOrderController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(value = "/get-order")
    public ResponseEntity<?> getOrderById(@RequestParam String id) {
        Order order = queryGateway.query(new FindByOrderIdQuery(id), Order.class).join();

        try {
            UUID uuid = UUID.fromString(id);
            System.out.println(uuid);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Not a valid order id", HttpStatus.BAD_REQUEST);
        }

        if (order == null) {
            return new ResponseEntity<>("No order with that id", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping(value = "/get-all-orders")
    public ResponseEntity<List> getAllOrders() {
        List orders =
                queryGateway.query(new FindAllOrdersQuery(), ResponseTypes.multipleInstancesOf(Order.class)).join();

        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/get-orders-by-customer-id")
    public ResponseEntity<List<Order>> getAllOrdersByCustomerId(@RequestParam String id) {
        List<Order> orders = queryGateway.query(new FindByCustomerIdQuery(id),
                ResponseTypes.multipleInstancesOf(Order.class)).join();

        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
