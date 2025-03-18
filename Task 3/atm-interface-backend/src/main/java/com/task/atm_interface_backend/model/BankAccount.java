package com.task.atm_interface_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balance;

    public BankAccount() { }

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
