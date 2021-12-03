package com.kata.bankAccount.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kata.bankAccount.dto.AccountDto;
import com.kata.bankAccount.dto.OperationDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Operation;

@Component
public class ObjectsMappers {

    public AccountDto mapEntityAccountToDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setBalance(account.getBalance());
        dto.setLibelle(account.getLibelle());
        List<OperationDto> operations = account.getOperations()
                .stream()
                .map(operation->mapEntityOperationToDto(operation))
                .sorted(Comparator.comparing(OperationDto::getDate).reversed()).collect(Collectors.toList());
        dto.setOperations(operations);
        return dto;
    }
    
    
    public OperationDto mapEntityOperationToDto(Operation operation) {
    	
    	return new OperationDto(operation.getId(), operation.getDate(), operation.getType(), operation.getAmount());
    }
    
    
}