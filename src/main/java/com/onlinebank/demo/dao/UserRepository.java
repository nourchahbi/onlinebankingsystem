package com.onlinebank.demo.dao;

import com.onlinebank.demo.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface UserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByUsername(String username);

	AppUser findByEmail(String email);
	
}
