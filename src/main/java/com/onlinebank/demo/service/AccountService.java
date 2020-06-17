package com.onlinebank.demo.service;

import com.onlinebank.demo.domain.AppRole;
import com.onlinebank.demo.domain.AppUser;
import org.springframework.stereotype.Service;

public interface AccountService {

	public AppUser save(String username, String password, String passwordconfirme, String email,String firstName,String lastName,String phone);
	public AppRole saveRole(AppRole role);
	public void addRoletoUser(String username, String roleName);
	public AppUser findUserByUsername(String username);

	
}
