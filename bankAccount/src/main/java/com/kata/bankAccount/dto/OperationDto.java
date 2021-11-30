package com.kata.bankAccount.dto;

import java.time.LocalDate;

import com.kata.bankAccount.entity.OperationType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class OperationDto {

	    private Long id;

	    private LocalDate date;

	    private OperationType type;

	    private Long amount;

		public OperationDto(Long id, LocalDate date, OperationType type, Long amount) {
			super();
			this.id = id;
			this.date = date;
			this.type = type;
			this.amount = amount;
		}

    

   


}
