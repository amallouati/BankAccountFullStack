package com.kata.bankAccount.entity;

public enum OperationType {

	DEPOSIT("deposit"), WITHDRAWAL("withdrawal");

	String operation;

	OperationType(String operation) {
		this.operation = operation;
	}
}
