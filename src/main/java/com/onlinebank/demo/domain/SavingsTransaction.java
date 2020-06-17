package com.onlinebank.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class SavingsTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private BigDecimal amount;
    private BigDecimal availableBalance;
    @ManyToOne
    private SavingsAccount savingsAccount;
}
