package com.task.atm_interface_backend.controller;

import com.task.atm_interface_backend.model.BankAccount;
import com.task.atm_interface_backend.model.TransactionRequest;
import com.task.atm_interface_backend.service.ATMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atm")
@CrossOrigin(origins = "http://localhost:3000")
public class ATMController {
    @Autowired
    private ATMService atmService;

    @GetMapping("/balance/{accountId}")
    public ResponseEntity<Double> checkBalance(@PathVariable int accountId) {
        double balance = atmService.checkBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/deposit")
    public ResponseEntity<BankAccount> deposit(@RequestBody TransactionRequest request) {
        BankAccount updatedAccount = atmService.deposit(request.getAccountId(), request.getAmount());
        return ResponseEntity.ok(updatedAccount);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccount> withdraw(@RequestBody TransactionRequest request) {
        BankAccount updatedAccount = atmService.withdraw(request.getAccountId(), request.getAmount());
        return ResponseEntity.ok(updatedAccount);
    }

}