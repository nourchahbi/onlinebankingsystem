package com.onlinebank.demo;

import com.onlinebank.demo.dao.UserRepository;
import com.onlinebank.demo.domain.AppRole;
import com.onlinebank.demo.domain.AppUser;
import com.onlinebank.demo.domain.Recipient;
import com.onlinebank.demo.service.AccountService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "OnlineBankingSystem API",version = "1.0"))
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private AccountService accountService;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args); }
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}




	@Override
	public void run(String... args) throws Exception {
		AppRole role1=new AppRole();
		role1.setRoleName("USER");
		accountService.saveRole(role1);
		AppRole role2=new AppRole();
		role2.setRoleName("ADMIN");
		accountService.saveRole(role2);

		AppUser user1 = new AppUser();
		accountService.save("noureddine1","123456", "123456", "chahbi.dev@gmail.com",
				"CHAHBI","Noureddine","0673673622");

        accountService.save("admin","123456", "123456", "admin@gmail.com",
                "admin","admin","0673673622");
        accountService.addRoletoUser("admin","ADMIN");


	}
}
