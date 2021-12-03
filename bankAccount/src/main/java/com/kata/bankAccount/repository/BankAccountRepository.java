package com.kata.bankAccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kata.bankAccount.entity.Account;

@Repository
public interface BankAccountRepository extends JpaRepository<Account, Long> {
}
