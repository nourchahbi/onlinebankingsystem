package com.onlinebank.demo.dao;

import com.onlinebank.demo.domain.SavingsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface SavingsTransactionRepository extends JpaRepository<SavingsTransaction,Long> {
}
