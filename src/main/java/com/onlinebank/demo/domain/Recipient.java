package com.onlinebank.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Recipient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String accountNumber;
    private String description;
    @ManyToOne
    @JsonIgnore
    private AppUser user;
}
