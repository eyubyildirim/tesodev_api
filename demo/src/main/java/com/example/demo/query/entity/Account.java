package com.example.demo.query.entity;

import lombok.Data;
import javax.persistence.Id;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Entity
public class Account {

    @Id
    private String accountId;
    private BigDecimal balance;
    private String status;

}
