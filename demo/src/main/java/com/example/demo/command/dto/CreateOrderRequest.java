package com.example.demo.command.dto;

import com.example.demo.query.entity.Address;
import com.example.demo.query.entity.Product;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class CreateOrderRequest {

    @Positive
    private Integer quantity = 0;
    @Positive
    private Double price = 0.0;
    @NotBlank
    private String customerId = "";
    private String status;
    @Field("product")
    @Valid
    private Product product;
    @Field("address")
    @Valid
    private Address address;

}
