package com.kata.bankAccount.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor

@Entity
@Table(name = "BANK_ACCOUNT")

public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "BALANCE")
    private Long balance ;
    
    private String libelle;
    
    @OneToMany(mappedBy = "account")
    public List<Operation> operations;
	 
		public Account (Long b) {
			
			balance=b;
			
		}
		
		public List<Operation> getOperations() {
	        return operations;
	    }

	    public void setOperations(List<Operation> operations) {
	        this.operations = operations;
	    }
}
