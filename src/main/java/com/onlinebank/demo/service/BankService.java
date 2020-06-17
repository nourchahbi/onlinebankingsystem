package com.onlinebank.demo.service;

import com.onlinebank.demo.domain.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface BankService {
    AppUser userByUsername(String username);
    void deposit(String username, BigDecimal amount,String accountType);
    void withdraw(String username, BigDecimal amount,String accountType);
    List<PrimaryTransaction> PrimaryTransactionByUser(String username);
    List<SavingsTransaction> SavingsTransactionByUser(String username);
    void TransferFromTo(String username,BigDecimal amount,String accouType,String accountType1);
    void addRecipientToUser(String username,String name,String email,String phone,String accountNumber,String description);
    Collection<Recipient> RecipientsUser(String username);
    void addAppointment(String username, Date date,String location, String description);
}
