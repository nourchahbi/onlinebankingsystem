package com.onlinebank.demo.service;

import com.onlinebank.demo.domain.AppRole;
import com.onlinebank.demo.domain.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {

	 AppUser save(String username, String password, String passwordconfirme, String email,String firstName,String lastName,String phone);
	 AppRole saveRole(AppRole role);
	 void addRoletoUser(String username, String roleName);
	 AppUser findUserByUsername(String username);
	 void deleteUser(String username);

	
}
