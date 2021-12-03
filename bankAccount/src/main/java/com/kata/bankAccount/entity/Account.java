package com.kata.bankAccount.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BANK_ACCOUNT")

public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "BALANCE")
	private Long balance;

	private String libelle;

	@OneToMany(mappedBy = "account")
	public List<Operation> operations;

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public Account(Long id, Long balance, String libelle, List<Operation> operations) {
		super();
		this.id = id;
		this.balance = balance;
		this.libelle = libelle;
		this.operations = operations;
	}
}
