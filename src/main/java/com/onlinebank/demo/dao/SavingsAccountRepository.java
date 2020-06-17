package com.onlinebank.demo.dao;

import com.onlinebank.demo.domain.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount,Long> {
}
