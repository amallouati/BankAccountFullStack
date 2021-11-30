package com.kata.bankAccount.test.services;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.kata.bankAccount.dto.AccountDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Operation;
import com.kata.bankAccount.entity.OperationType;
import com.kata.bankAccount.exception.OperationFailException;
import com.kata.bankAccount.repository.BankAccountRepository;
import com.kata.bankAccount.service.impl.AccountServiceImpl;
import com.kata.bankAccount.services.ObjectsMappers;



 @RunWith(SpringRunner.class)

public class AccountServiceTest {
	    @Mock
	    private BankAccountRepository bankAccountRepository;
	    @Mock
	    private ObjectsMappers objectsMappers;
	    
	    @InjectMocks
	    private AccountServiceImpl accountService;

	    private List<Operation> operations;
	    private Account account ;
	    
	    @Before
	    public void setup(){
	        account = new Account();
	        account.setBalance(5000L);
	        account.setId(12L);
	        operations = new ArrayList<>();
	        operations.add(new Operation(LocalDate.now(), OperationType.DEPOSIT,10000L,account));
	        account.setOperations(operations);
	    }
	    
	    
	    
	
	    @Test(expected = OperationFailException.class)
	     public void printStatement_throw_exception_for_no_such_account() throws OperationFailException {
	        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.empty());
	        accountService.printStatement(12L);
	        Assert.fail("should have thrown OperationFailException ");
	       
	    }	    
	    
	    
	    @Test
	    public void printStatement_return_current_account_balance() throws OperationFailException {
	        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.of(account));
	        when(objectsMappers.mapEntityAccountToDto(any(Account.class))).thenCallRealMethod();
	        when(objectsMappers.mapEntityOperationToDto(any(Operation.class))).thenCallRealMethod();
	        AccountDto accountDto = accountService.printStatement(12L);
	        assertThat(accountDto.getBalance()).isEqualTo(account.getBalance());
	        assertThat(accountDto.getOperations()).isNotEmpty();
	        assertThat(accountDto.getOperations()).hasSameSizeAs(account.getOperations());

	       Operation operation = new Operation(LocalDate.now(), OperationType.DEPOSIT,10000L,account);
	        account.getOperations().add(operation);
	        when(bankAccountRepository.findById(anyLong())).thenReturn(Optional.of(account));
	        accountDto = accountService.printStatement(12L);
	        assertThat(accountDto.getOperations()).hasSize(2);
	  // assertThat(accountDto.getOperations()).isSortedAccordingTo(Comparator.comparing(Operation::getDate).reversed());

	    }
	    
	    
	    
	    
	    
	    
	    
}
