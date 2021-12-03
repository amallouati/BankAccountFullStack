package com.kata.bankAccount.dto;

import java.time.LocalDateTime;

import com.kata.bankAccount.entity.OperationType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class OperationDto {

	private Long id;

	private LocalDateTime date;

	private OperationType type;

	private Long amount;

	public OperationDto(Long id, LocalDateTime date, OperationType type, Long amount) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.amount = amount;
	}

}
