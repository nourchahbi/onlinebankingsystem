package com.onlinebank.demo.web;

import com.onlinebank.demo.domain.PrimaryTransaction;
import com.onlinebank.demo.domain.Recipient;
import com.onlinebank.demo.domain.SavingsTransaction;
import com.onlinebank.demo.service.BankService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/deposit")
    public void deposit(@RequestBody DepositForm depositForm){
        bankService.deposit(depositForm.getUsername(),depositForm.getAmount(),depositForm.getAccount());
    }
    @PostMapping("/withdraw")
    public void withdraw(@RequestBody DepositForm depositForm){
        bankService.withdraw(depositForm.getUsername(),depositForm.getAmount(),depositForm.getAccount());
    }
    @PostMapping("/primaryTransactions")
    public List<PrimaryTransaction> TransactionByUsername(@RequestBody UserN userN){
    return bankService.PrimaryTransactionByUser(userN.getUsername());
    }
    @PostMapping("/savingsTransactions")
    public List<SavingsTransaction> SavingsByUsername(@RequestBody UserN user){
        return bankService.SavingsTransactionByUser(user.getUsername());
    }
    @PostMapping("/transfer")
    public void Transfer(@RequestBody TransferFromTo transferFromTo){
        bankService.TransferFromTo(transferFromTo.getUsername(),transferFromTo.getAmount(),transferFromTo.getAccount(),transferFromTo.getAccount1());
    }
    @PostMapping("/addRecipient")
    public void addRecipientUser(@RequestBody AddRecipient addRecipient){
       bankService.addRecipientToUser(addRecipient.getUsername(),addRecipient.getName(),addRecipient.getEmail(),addRecipient.getPhone(),addRecipient.getAccountNumber(),addRecipient.getDescription());
    }
    @PostMapping("/recipientslist")
    @ResponseBody
    public Collection<Recipient> RecipientsByUser(@RequestBody UserN userNa){
        return bankService.RecipientsUser(userNa.getUsername());
    }
    @PostMapping("/addAppointment")
    public void addAppointmentUser(@RequestBody AddAppointment addAppointment){
        bankService.addAppointment(addAppointment.getUsername(),addAppointment.getDate(),addAppointment.getLocation(),addAppointment.getDescription());
    }
}
@Data
class DepositForm{
    private String account;
    private BigDecimal amount;
    private String username;
}
@Data
class UserN{
    private String username;
}
@Data
class TransferFromTo{
    private String account;
    private String account1;
    private String username;
    private BigDecimal amount;
}
@Data
class AddRecipient{
    private String username;
    private String name;
    private String email;
    private String phone;
    private String accountNumber;
    private String description;
}
@Data
class AddAppointment{
    private String username;
    private Date date;
    private String location;
    private String description;

}