package com.example.demo.query.repository;

import com.example.demo.query.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<List<Order>> findAllByCustomerId(String customerId);
}
