package com.kata.bankAccount.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kata.bankAccount.dto.AccountDto;
import com.kata.bankAccount.dto.OperationDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Operation;
import com.kata.bankAccount.entity.OperationType;
import com.kata.bankAccount.exception.OperationFailException;

public interface OperationService {

	/**
	 * Save an Operation.
	 *
	 * @param Operation the entity to save.
	 * @return the persisted entity.
	 */
	Operation save(Operation contrat);

	/**
	 * Get all the Operation.
	 *
	 * @param pageable the pagination information.
	 * @return the list of entities.
	 */
	Page<Operation> findAll(Pageable pageable);

	/**
	 * Get the "id" Operation.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	Optional<Operation> findOne(Long id);

	/**
	 * Delete the "id" Operation.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id);

	/**
	 * find all account operation
	 * 
	 * @param id
	 */
	List<OperationDto> findAllByAccountId(Long id);

	/**
	 * add a deposit operation
	 * 
	 * @param accountId
	 * @param amount
	 * @return
	 * @throws OperationFailException
	 */
	AccountDto doTransaction(Long accountId, OperationDto operation) throws OperationFailException;

	/**
	 * @param account
	 * @param amount
	 * @param operationType
	 * @return
	 * @throws OperationFailException
	 */

	Operation createAndPerformOperation(Account account, Long amount, OperationType operationType)
			throws OperationFailException;

}
