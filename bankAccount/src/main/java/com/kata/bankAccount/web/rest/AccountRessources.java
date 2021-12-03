package com.kata.bankAccount.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bankAccount.dto.AccountDto;

import com.kata.bankAccount.services.AccountService;
import com.kata.bankAccount.services.OperationService;

@RestController
@RequestMapping("/api/accounts/")
@CrossOrigin
public class AccountRessources {

	private final AccountService bankAccountService;
	private final OperationService operationService;

	public AccountRessources(AccountService bankAccountService, OperationService operationService) {
		this.bankAccountService = bankAccountService;
		this.operationService = operationService;
	}

	@GetMapping("all")
	public List<AccountDto> getAllAccounts() {
		return bankAccountService.findAll();
	}

}
