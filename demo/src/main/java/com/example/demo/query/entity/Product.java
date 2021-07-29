package com.example.demo.query.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {

    @Id
    @NotBlank
    private String _id = "";
    @NotBlank
    private String imageUrl = "";
    @NotBlank
    private String name = "";
}
