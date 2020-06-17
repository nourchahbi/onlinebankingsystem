package com.onlinebank.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    private boolean enabled=true;
    @OneToOne(cascade = {CascadeType.ALL})
    private PrimaryAccount primaryAccount;
    @OneToOne(cascade = {CascadeType.ALL})
    private SavingsAccount savingsAccount;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Appointment> appointmentList;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
    private Collection<Recipient> recipientList=new ArrayList<>() ;

    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<AppRole> roles=new ArrayList<AppRole>();

}
