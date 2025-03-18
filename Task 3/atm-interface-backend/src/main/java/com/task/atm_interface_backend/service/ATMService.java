package com.task.atm_interface_backend.service;

import com.task.atm_interface_backend.exception.AccountNotFoundException;
import com.task.atm_interface_backend.exception.InsufficientFundsException;
import com.task.atm_interface_backend.model.BankAccount;
import com.task.atm_interface_backend.repository.ATMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ATMService {

    @Autowired
    private ATMRepository atmRepository;

    public BankAccount deposit(int accountId, double amount) {
        BankAccount account = atmRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        return atmRepository.save(account);
    }

    public BankAccount withdraw(int accountId, double amount) {
        BankAccount account = atmRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        return atmRepository.save(account);
    }

    public double checkBalance(int accountId) {
        BankAccount account = atmRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return account.getBalance();
    }
}