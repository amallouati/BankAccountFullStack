package com.kata.bankAccount.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class AccountDto {
	private Long id;
	private Long balance;
	private String libelle;
	private List<OperationDto> operations;
	public AccountDto(Long id, Long balance, String libelle, List<OperationDto> operations) {
		super();
		this.id = id;
		this.balance = balance;
		this.libelle = libelle;
		this.operations = operations;
	}
	
	

}
