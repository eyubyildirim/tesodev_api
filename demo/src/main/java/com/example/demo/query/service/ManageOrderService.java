package com.example.demo.query.service;

import com.example.demo.common.event.OrderCreatedEvent;
import com.example.demo.common.event.OrderUpdatedEvent;
import com.example.demo.query.entity.Order;
import com.example.demo.query.query.FindAccountByIdQuery;
import com.example.demo.query.query.FindAllOrdersQuery;
import com.example.demo.query.query.FindByCustomerIdQuery;
import com.example.demo.query.query.FindByOrderIdQuery;
import com.example.demo.query.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ManageOrderService {

    private final OrderRepository orderRepository;

    public ManageOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        log.info("Handling OrderCreatedEvent...");

        try {
            Order order = new Order();
            order.set_id(event.getId());
            order.setCustomerId(event.getCustomerId());
            order.setQuantity(event.getQuantity());
            order.setPrice(event.getPrice());
            order.setStatus(event.getStatus());
            order.setProduct(event.getProduct());
            order.setAddress(event.getAddress());
            order.setCreatedAt(event.getCreatedAt());
            order.setUpdatedAt(event.getUpdatedAt());

            orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void on(OrderUpdatedEvent event) {
        log.info("Handling OrderUpdatedEvent...");
        log.info(event.getId());
        orderRepository.findById(event.getId()).ifPresent(order -> {
            order.setQuantity(event.getQuantity());
            order.setPrice(event.getPrice());
            order.setAddress(event.getAddress());
            order.setProduct(event.getProduct());
            order.setStatus(event.getStatus());
            order.setUpdatedAt(event.getUpdatedAt());

            orderRepository.save(order);
        });
    }

    @QueryHandler
    public Order handle(FindByOrderIdQuery query) {
        log.info("Handling FindByOrderIdQuery...");

        return orderRepository.findById(query.getOrderId()).orElse(null);
    }

    @QueryHandler
    public List<Order> handle(FindAllOrdersQuery query) {
        log.info("Handling FindAllOrdersQuery...");

        return orderRepository.findAll();
    }

    @QueryHandler
    public List<Order> handle(FindByCustomerIdQuery query) {
        log.info("Handling FindByCustomerIdQuery...");

        return orderRepository.findAllByCustomerId(query.getCustomerId()).orElse(new ArrayList<>());
    }
}
