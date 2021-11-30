package com.kata.bankAccount.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kata.bankAccount.dto.AccountDto;
import com.kata.bankAccount.dto.OperationDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Operation;
import com.kata.bankAccount.entity.OperationType;
import com.kata.bankAccount.exception.OperationFailException;
import com.kata.bankAccount.repository.BankAccountRepository;
import com.kata.bankAccount.repository.OperationRepository;
import com.kata.bankAccount.services.ObjectsMappers;
import com.kata.bankAccount.services.OperationService;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {
	
	private final OperationRepository operationRepository;
	private final BankAccountRepository bankAccountRepository;
	 private final ObjectsMappers dtoMapper;
	 
	 
	 public OperationServiceImpl(OperationRepository operationRepository, BankAccountRepository bankAccountRepository, ObjectsMappers dtoMapper) {
	        this.operationRepository = operationRepository;
	        this.bankAccountRepository = bankAccountRepository;
	        this.dtoMapper = dtoMapper;
	    }
	
	
	
	/**
     * deposit the specified amount into the specified account
     * @param accountId the account identifier
     * @param amount the amount of the transaction
     * @throws OperationFailException
     */
	 @Transactional
    public AccountDto doTransaction(Long accountId, OperationDto operation) throws OperationFailException {
    	Account account = bankAccountRepository.findById(accountId).get();
    	 createAndPerformOperation(account, Math.abs(operation.getAmount()), operation.getType());
    	 // update account balance
    	switch(operation.getType()) {
    	case  DEPOSIT : account.setBalance(account.getBalance()+operation.getAmount());break;
    	case  WITHDRAWAL :account.setBalance(account.getBalance()-operation.getAmount());break;
    	}
       
        
        return dtoMapper.mapEntityAccountToDto(account);
    }
	
    /**
     *
     */
    @Override
	public Operation save(Operation operation) {
		
		return operationRepository.save(operation);
	}

	/**
	 *
	 */
	@Override
	public Page<Operation> findAll(Pageable pageable) {
	
		return operationRepository.findAll(pageable);
	}

	/**
	 *
	 */
	@Override
	public Optional<Operation> findOne(Long id) {
	
		return operationRepository.findById(id);
	}

	/**
	 *
	 */
	@Override
	public void delete(Long id) {
		
		operationRepository.deleteById(id);
	}

	/**
	 *
	 */
	@Override
	public List<OperationDto> findAllByAccountId(Long id) {
		return operationRepository.findAllByAccountId(id)
				.stream()
				.map(operation ->dtoMapper.mapEntityOperationToDto(operation))
				.collect(Collectors.toList());
	}



	/**
	 *
	 */
	@Override
	public Operation createAndPerformOperation(Account account, Long amount, OperationType operationType)
			throws OperationFailException {
		if(operationType.equals(OperationType.WITHDRAWAL) && account.getBalance()<amount) {
			throw new OperationFailException("insufficient balance");
		}
		Operation operation=new Operation(LocalDate.now(), operationType, amount, account);
		return operationRepository.save(operation);
	}

}
