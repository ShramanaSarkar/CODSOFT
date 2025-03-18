package com.task.atm_interface_backend.repository;

import com.task.atm_interface_backend.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ATMRepository extends JpaRepository<BankAccount, Integer> {
}
