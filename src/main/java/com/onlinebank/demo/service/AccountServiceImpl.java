package com.onlinebank.demo.service;

import com.onlinebank.demo.dao.PrimaryAccountRepository;
import com.onlinebank.demo.dao.RoleRepository;
import com.onlinebank.demo.dao.SavingsAccountRepository;
import com.onlinebank.demo.dao.UserRepository;
import com.onlinebank.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrimaryAccountRepository primaryAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Override
    public AppUser save(String username, String password, String passwordconfirme, String email,String firstName,String lastName,String phone) {
        AppUser user = userRepository.findByUsername(username);
        AppUser useremail=userRepository.findByEmail(email);
        if(user!=null) throw new RuntimeException("User Already exists");
        if(!password.equals(passwordconfirme)) throw new RuntimeException("Please confirm your password");
        if(email.isEmpty()) throw new RuntimeException("Please Enter your Email");
        if(useremail!=null) throw new RuntimeException("This email address has already subscribed");
        if(firstName.isEmpty()) throw new RuntimeException("Please Enter your FirstName");
        if(lastName.isEmpty()) throw new RuntimeException("Please Enter your LastName");
        if(phone.isEmpty()) throw new RuntimeException("Please Enter your Phone");
        AppUser appuser = new AppUser();
        appuser.setUsername(username);
        appuser.setEnabled(true);
        appuser.setEmail(email);
        appuser.setFirstName(firstName);
        appuser.setLastName(lastName);
        appuser.setPhone(phone);
        appuser.setPrimaryAccount(new PrimaryAccount());
        appuser.setSavingsAccount(new SavingsAccount());
        PrimaryAccount primaryAccount=appuser.getPrimaryAccount();
        primaryAccount.setAccountNumber(primaryAccount.getAccountNumber()+2);
        primaryAccountRepository.save(primaryAccount);
        SavingsAccount savingsAccount=appuser.getSavingsAccount();
        savingsAccount.setAccountNumber(savingsAccount.getAccountNumber()+2);
        savingsAccountRepository.save(savingsAccount);
        String hashPassword = passwordEncoder().encode(password);
        appuser.setPassword(hashPassword);
        userRepository.save(appuser);
        addRoletoUser(username,"USER");
        return appuser;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoletoUser(String username, String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        AppUser user = userRepository.findByUsername(username);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(String username) {
        AppUser user =userRepository.findByUsername(username);
        userRepository.delete(user);
    }
}
