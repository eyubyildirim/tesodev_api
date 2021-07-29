package com.example.demo.query.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class Address {

    private String addressLine = "";
    @NotBlank
    private String city = "";
    @NotBlank
    private String country = "";
    @NotBlank
    private String cityCode = "";
}
