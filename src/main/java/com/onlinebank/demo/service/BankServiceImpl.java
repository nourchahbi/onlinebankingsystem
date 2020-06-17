package com.onlinebank.demo.service;

import com.onlinebank.demo.dao.*;
import com.onlinebank.demo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BankServiceImpl implements BankService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    private PrimaryAccountRepository primaryAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
    private PrimaryTransactionRepository primaryTransactionRepository;
    @Autowired
    private SavingsTransactionRepository savingsTransactionRepository;
    @Autowired
    private RecipientRepository recipientRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public AppUser userByUsername(String username) {
        AppUser user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public void deposit(String username, BigDecimal amount,String accountType) {
        AppUser user = userRepository.findByUsername(username);
        if(accountType.equals("PrimaryAccount")){
            PrimaryAccount primaryAccount=user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(amount));
            PrimaryTransaction primaryTransaction=new PrimaryTransaction();
            primaryTransaction.setAmount(amount);
            primaryTransaction.setAvailableBalance(primaryAccount.getAccountBalance());
            primaryTransaction.setDate(new Date());
            primaryTransaction.setDescription("Deposit to Primary Account");
            primaryTransaction.setStatus("Finished");
            primaryTransaction.setType("Account");
            primaryTransaction.setPrimaryAccount(primaryAccount);
            primaryTransactionRepository.save(primaryTransaction);
            primaryAccount.getPrimaryTransactionList().add(primaryTransaction);
            primaryAccountRepository.save(primaryAccount);
            userRepository.save(user);
        }
        else if (accountType.equals("SavingsAccount")){
            SavingsAccount savingsAccount=user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(amount));
            SavingsTransaction savingsTransaction=new SavingsTransaction();
            savingsTransaction.setAmount(amount);
            savingsTransaction.setAvailableBalance(savingsAccount.getAccountBalance());
            savingsTransaction.setDate(new Date());
            savingsTransaction.setDescription("Deposit to Savings Account");
            savingsTransaction.setStatus("Finished");
            savingsTransaction.setType("Account");
            savingsTransaction.setSavingsAccount(savingsAccount);
            savingsTransactionRepository.save(savingsTransaction);
            savingsAccount.getSavingsTransactions().add(savingsTransaction);
            savingsAccountRepository.save(savingsAccount);
            userRepository.save(user);
        }

    }
    @Override
    public void withdraw(String username, BigDecimal amount,String accountType) {
        AppUser user = userRepository.findByUsername(username);
        if(accountType.equals("PrimaryAccount")){
            PrimaryAccount primaryAccount=user.getPrimaryAccount();
            if(primaryAccount.getAccountBalance().compareTo(amount)<0){
            throw new RuntimeException("You don't have enough money to withdraw");
            }else{
                primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(amount));
                PrimaryTransaction primaryTransaction=new PrimaryTransaction();
                primaryTransaction.setAmount(amount);
                primaryTransaction.setAvailableBalance(primaryAccount.getAccountBalance());
                primaryTransaction.setDate(new Date());
                primaryTransaction.setDescription("Withdraw from Primary Account");
                primaryTransaction.setStatus("Finished");
                primaryTransaction.setType("Account");
                primaryTransaction.setPrimaryAccount(primaryAccount);
                primaryTransactionRepository.save(primaryTransaction);
                primaryAccount.getPrimaryTransactionList().add(primaryTransaction);
                primaryAccountRepository.save(primaryAccount);
                userRepository.save(user);
            }

        }
        else if (accountType.equals("SavingsAccount")){
            SavingsAccount savingsAccount=user.getSavingsAccount();
            if(savingsAccount.getAccountBalance().compareTo(amount)<0){
                throw new RuntimeException("You don't have enough money to withdraw");
            }else {
                savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(amount));
                SavingsTransaction savingsTransaction=new SavingsTransaction();
                savingsTransaction.setAmount(amount);
                savingsTransaction.setAvailableBalance(savingsAccount.getAccountBalance());
                savingsTransaction.setDate(new Date());
                savingsTransaction.setDescription("Withdraw from Savings Account");
                savingsTransaction.setStatus("Finished");
                savingsTransaction.setType("Account");
                savingsTransaction.setSavingsAccount(savingsAccount);
                savingsTransactionRepository.save(savingsTransaction);
                savingsAccount.getSavingsTransactions().add(savingsTransaction);
                savingsAccountRepository.save(savingsAccount);
                userRepository.save(user);
            }
        }

    }
    @Override
    public List<PrimaryTransaction> PrimaryTransactionByUser(String username) {
        AppUser user1 = userByUsername(username);
        return user1.getPrimaryAccount().getPrimaryTransactionList();
    }

    @Override
    public List<SavingsTransaction> SavingsTransactionByUser(String username) {
        AppUser user2 = userByUsername(username);
        return user2.getSavingsAccount().getSavingsTransactions();
    }

    @Override
    public void TransferFromTo(String username, BigDecimal amount, String accounType, String accountType1) {
       withdraw(username,amount,accounType);
       deposit(username,amount,accountType1);
    }

    @Override
    public void addRecipientToUser(String username, String name,String email,String phone,String accountNumber,String description) {
        AppUser user=userRepository.findByUsername(username);
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty()||accountNumber.isEmpty()||description.isEmpty()) {
        throw new RuntimeException("Please fill the form !!");
        }
        Recipient recipientuser=new Recipient();
        recipientuser.setName(name);
        recipientuser.setAccountNumber(accountNumber);
        recipientuser.setDescription(description);
        recipientuser.setEmail(email);
        recipientuser.setPhone(phone);
        recipientuser.setUser(user);
        user.getRecipientList().add(recipientuser);
        recipientRepository.save(recipientuser);
        userRepository.save(user);
    }

    @Override
    public Collection<Recipient> RecipientsUser(String username) {
        AppUser user = userRepository.findByUsername(username);
        return user.getRecipientList();
    }

    @Override
    public void addAppointment(String username, Date date, String location, String description) {
        AppUser user=userRepository.findByUsername(username);
        if(date==null) throw new RuntimeException("Please select a date");
        if(location.isEmpty()) throw new RuntimeException("Please enter a location");
        if(description.isEmpty()) throw new RuntimeException("Please enter a description");
        Appointment appointment=new Appointment();
        appointment.setDate(date);
        appointment.setLocation(location);
        appointment.setDescription(description);
        appointment.setConfirmed(true);
        appointment.setUser(user);
        user.getAppointmentList().add(appointment);
        appointmentRepository.save(appointment);
        userRepository.save(user);

    }
}
