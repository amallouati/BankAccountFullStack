package com.kata.bankAccount.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kata.bankAccount.dto.AccountDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.exception.OperationFailException;
import com.kata.bankAccount.repository.BankAccountRepository;
import com.kata.bankAccount.services.AccountService;
import com.kata.bankAccount.services.ObjectsMappers;



@Service
public class AccountServiceImpl implements AccountService {

	private final BankAccountRepository accountRepository;
	private final ObjectsMappers dtoMapper;

	public AccountServiceImpl(BankAccountRepository accountRepository,ObjectsMappers dtoMapper) {
		this.accountRepository = accountRepository;
		this.dtoMapper=dtoMapper;
	}

	
	/**
    *
    * @param accountId account identifier
    * @return  the account state including operations
    * @throws OperationFailException
    */
   public AccountDto printStatement(Long accountId) throws OperationFailException {
       Optional<Account> optionalBankAccount = accountRepository.findById(accountId);
       if(!optionalBankAccount.isPresent()){
           throw new OperationFailException(": "+accountId);
       }
       return dtoMapper.mapEntityAccountToDto(optionalBankAccount.get());
   }
	

	@Override
	public Account save(Account account) {
		
		return accountRepository.save(account);
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {

		return accountRepository.findAll(pageable);

	}

	@Override
	public Optional<Account> findOne(Long id) {
		
		return accountRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		accountRepository.deleteById(id);
		
	}

	@Override
	public List<AccountDto> findAll() {
		return accountRepository.findAll().stream().map(account->dtoMapper.mapEntityAccountToDto(account)).collect(Collectors.toList());
	}

}
