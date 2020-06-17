package com.onlinebank.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class PrimaryTransaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private BigDecimal amount;
    private BigDecimal availableBalance;
    @ManyToOne
    @JsonIgnore
    private PrimaryAccount primaryAccount;

}
