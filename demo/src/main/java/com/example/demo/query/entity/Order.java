package com.example.demo.query.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String _id;
    @Positive
    private Integer quantity;
    @Positive
    private Double price;
    @NotBlank
    private String customerId;
    private String status;
    @Field("product")
    @Valid
    private Product product;
    @Field("address")
    @Valid
    private Address address;
    private String createdAt;
    private String updatedAt;

}
