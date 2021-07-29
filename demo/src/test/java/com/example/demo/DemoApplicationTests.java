package com.example.demo;

import com.example.demo.query.controller.ManageOrderController;
import com.example.demo.query.entity.Address;
import com.example.demo.query.entity.Order;
import com.example.demo.query.query.FindByOrderIdQuery;
import com.example.demo.query.repository.OrderRepository;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MongoValidationSpringBootTest {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private QueryGateway queryGateway;

    private final String orderId = "9208b1a8-ae28-462a-905d-a9167e9221c7";
    private final String invalidOrderId = "9208b1a8-ae28-462a-905d-a9167e9221c";
    private final String nonExistentOrderId = "9208b1a8-ae28-462a-905d-a9167e9231c7";
    private final String customerId = "43856df4-f118-4fa7-aff2-10d3ce8b7044";


    @Test
    void shouldFailOnInvalidEntity() {
        Order invalidDocument = new Order();
        invalidDocument.set_id(UUID.randomUUID().toString());
        invalidDocument.setAddress(new Address("null", null, "asdasd", "adasd"));
        assertThatThrownBy(() -> repository.save(invalidDocument)).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    void shouldGetOrder() {
        Order order = queryGateway.query(new FindByOrderIdQuery(orderId), Order.class).join();
        Assertions.assertNotNull(order);
        Assertions.assertEquals(order.getPrice(), 523.3);
        Assertions.assertEquals(order.getQuantity(), 5);
    }

    @Test
    void shouldGetOrders() {

    }

    @Test
    void shouldGetResponseEntityWithOrder() {
        ManageOrderController controller = new ManageOrderController(queryGateway);
        ResponseEntity res = controller.getOrderById(orderId);
        System.out.println(res.toString());
    }

    @Test
    void shouldGetResponseEntityWithInvalidOrderId() {
        ManageOrderController controller = new ManageOrderController(queryGateway);
        ResponseEntity res = controller.getOrderById(invalidOrderId);
        System.out.println(res.toString());
    }

    @Test
    void shouldGetResponseEntityWithOrderNotFound() {
        ManageOrderController controller = new ManageOrderController(queryGateway);
        ResponseEntity res = controller.getOrderById(nonExistentOrderId);
        System.out.println(res.toString());
    }

    @Test
    void shouldUpdateOrder() {
        
    }
}

