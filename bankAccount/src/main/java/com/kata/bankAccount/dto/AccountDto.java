package com.kata.bankAccount.dto;

import java.util.List;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor

public class AccountDto {
	 private Long id;
	 private Long balance;
	 private String libelle;
     private List<OperationDto> operations;
   
   
 }
