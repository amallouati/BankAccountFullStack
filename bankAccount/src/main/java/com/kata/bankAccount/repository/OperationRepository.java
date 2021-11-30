package com.kata.bankAccount.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kata.bankAccount.entity.Operation;



	
	
	@Repository
	public interface OperationRepository extends JpaRepository<Operation,Long> {
		
		
		/**
		 * get All operations account
		 * @param id
		 * @return
		 */
		List<Operation> findAllByAccountId(Long id);
		
	}
	