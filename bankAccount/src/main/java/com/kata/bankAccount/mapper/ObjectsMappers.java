package com.kata.bankAccount.mapper;

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

	public AccountDto mapEntityAccountToDto(Account account) {

		List<OperationDto> operations = account.getOperations().stream()
				.map(operation -> mapEntityOperationToDto(operation))
				.sorted(Comparator.comparing(OperationDto::getDate).reversed()).collect(Collectors.toList());

		AccountDto dto = AccountDto.builder().id(account.getId()).balance(account.getBalance())
				.libelle(account.getLibelle()).operations(operations).build();

		return dto;
	}

	public OperationDto mapEntityOperationToDto(Operation operation) {
		return OperationDto.builder().id(operation.getId()).date(operation.getDate()).type(operation.getType())
				.amount(operation.getAmount()).build();

	}

}