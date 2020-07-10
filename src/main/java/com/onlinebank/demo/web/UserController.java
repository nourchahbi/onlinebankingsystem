package com.onlinebank.demo.web;

import com.onlinebank.demo.domain.AppUser;
import com.onlinebank.demo.service.AccountService;
import com.onlinebank.demo.service.BankService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private BankService bankService;

	@PostMapping("/register")
	public AppUser register(@RequestBody userForm userform) {
		
		return accountService.save(userform.getUsername(), userform.getPassword(), userform.getPasswordconfirme(),
				userform.getEmail(),userform.getFirstName(),userform.getLastName(),userform.getPhone());
	}
	@PostMapping("/user")
	public AppUser userByUsername(@RequestBody UserName userName){
		return bankService.userByUsername(userName.getUsername());
	}

	@PostMapping("/delete")
	public void deleteByUsername(@RequestBody String userName){
		accountService.deleteUser(userName);
	}

}
@Data
class UserName{
	private String username;
}
@Data
class userForm {
	private String username;
	private String password;
	private String passwordconfirme;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
}
