package com.kata.bankAccount.test.services;


import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;

import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;
import java.util.Comparator;


import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.kata.bankAccount.dto.AccountDto;
import com.kata.bankAccount.dto.OperationDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Operation;
import com.kata.bankAccount.entity.OperationType;
import com.kata.bankAccount.exception.OperationFailException;
import com.kata.bankAccount.mapper.ObjectsMappers;
import com.kata.bankAccount.repository.BankAccountRepository;
import com.kata.bankAccount.repository.OperationRepository;
import com.kata.bankAccount.service.impl.OperationServiceImpl;

@RunWith(SpringRunner.class)
public class OperationServiceTest {
	
	

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private OperationRepository operationRepository;

    @Mock
    private ObjectsMappers objectsMappers;

    @InjectMocks
    private OperationServiceImpl operationService;

    private Account account ;
    private Operation operation;
    private Operation operationWithdrawal;
    private OperationDto operation2;
    private OperationDto operationWithdrawalDtoFail;
    private OperationDto operationWithdrawalDtoSuccess;
    @Before
    public void setUp(){
        account = new Account();
        account.setBalance(5000L);
        account.setId(12L);
        account.setOperations(new ArrayList<>());
		operation = new Operation(LocalDateTime.now(), OperationType.DEPOSIT,10000L, null);
        operationWithdrawal=new Operation(LocalDateTime.now(), OperationType.WITHDRAWAL,1000L, null);
        operationWithdrawalDtoFail=new OperationDto(1L,LocalDateTime.now(), OperationType.WITHDRAWAL,6000L);
        operationWithdrawalDtoSuccess=new OperationDto(1L,LocalDateTime.now(), OperationType.WITHDRAWAL,2000L);
        operation2 = new OperationDto(1L,LocalDateTime.now(), OperationType.DEPOSIT,1200L);
        OperationDto operation=new OperationDto();
        operation.setAmount(1200L);
    }

    @Test(expected = OperationFailException.class)
    public void createAndPerformOperation_throw_OperationFailException() throws OperationFailException {
        operationService.createAndPerformOperation(account,6000L,OperationType.WITHDRAWAL);
        Assert.fail("should have thrown OperationFailException ");

    }

    @Test
    public void createAndPerformOperation_perform_deposit() throws OperationFailException {
    	 when(operationRepository.save(any(Operation.class))).thenReturn(operation);
        Operation operation = operationService.createAndPerformOperation(account,10000L,OperationType.DEPOSIT);
        assertThat(operation.getAmount()).isEqualTo(10000);
        assertThat(operation.getType()).isEqualTo(OperationType.DEPOSIT);
   
    }

    @Test
    public void createAndPerformOperation_perform_withdrawal() throws OperationFailException {
    	 when(operationRepository.save(any(Operation.class))).thenReturn(operationWithdrawal);
        Operation operation = operationService.createAndPerformOperation(account,1000L,OperationType.WITHDRAWAL);
        assertThat(operation.getAmount()).isEqualTo(1000);
        assertThat(operation.getType()).isEqualTo(OperationType.WITHDRAWAL);
  
    }


    @Test
    public void doDeposit_perform_deposit_and_save_operation() throws OperationFailException {
        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.of(account));
        when(objectsMappers.mapEntityAccountToDto(any(Account.class))).thenCallRealMethod();
        when(operationRepository.save(any(Operation.class))).thenReturn(operation);
        long currentAccountBalance = account.getBalance();
        AccountDto dto = operationService.doTransaction(12L,operation2);
        assertThat(dto.getBalance()).isEqualTo(currentAccountBalance+1200);
    }

    @Test(expected = OperationFailException.class)
    public void doWithdrawal_throw_OperationFailException() throws OperationFailException {
        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.of(account));
        operationService.doTransaction(12L,operationWithdrawalDtoFail);
        Assert.fail("should have thrown OperationFailException ");
    }

    @Test
    public void doWithdrawal_perform_withdrawal_and_save_operation() throws OperationFailException {
        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.of(account));
        when(objectsMappers.mapEntityAccountToDto(any(Account.class))).thenCallRealMethod();
        when(operationRepository.save(any(Operation.class))).thenReturn(operation);
        long currentAccountBalance = account.getBalance();
        AccountDto dto = operationService.doTransaction(12L,operationWithdrawalDtoSuccess);
        assertThat(dto.getBalance()).isEqualTo(currentAccountBalance-2000);
    }

	
	
	
	
	
	

}
