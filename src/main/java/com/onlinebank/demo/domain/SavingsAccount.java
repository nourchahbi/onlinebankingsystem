package com.onlinebank.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class SavingsAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int accountNumber=0;
    private BigDecimal accountBalance=BigDecimal.ZERO;
    @OneToMany(mappedBy = "savingsAccount")
    @JsonIgnore
    private List<SavingsTransaction> savingsTransactions=new ArrayList<>();
}
