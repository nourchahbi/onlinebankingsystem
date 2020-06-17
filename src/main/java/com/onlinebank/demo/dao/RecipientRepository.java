package com.onlinebank.demo.dao;

import com.onlinebank.demo.domain.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface RecipientRepository extends JpaRepository<Recipient,Long> {
}
