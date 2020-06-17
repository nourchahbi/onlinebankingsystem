package com.onlinebank.demo.dao;

import com.onlinebank.demo.domain.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<AppRole, Long> {

	public AppRole findByRoleName(String roleName);
}
