package com.kata.bankAccount.exception;

public class OperationFailException extends Exception {

	String message;

	public OperationFailException(String message) {
		super(message);
		this.message = message;
	}

}
