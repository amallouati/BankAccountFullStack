package com.kata.bankAccount.entity;

import java.io.Serializable;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "operation")
public class Operation implements Serializable {

	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime date;

	@Enumerated(EnumType.STRING)
	private OperationType type;

	private Long amount;

	@ManyToOne
	private Account account;

	public Operation() {
	}

	public Operation(LocalDateTime date, OperationType type, Long amount, Account account) {
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.account = account;
	}

}
