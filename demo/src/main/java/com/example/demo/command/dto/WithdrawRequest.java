package com.example.demo.command.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequest {

    private String accountId;
    private BigDecimal amount;
}
