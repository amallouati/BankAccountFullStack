package com.kata.bankAccount.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kata.bankAccount.dto.AccountDto;
import com.kata.bankAccount.entity.Account;
import com.kata.bankAccount.entity.Operation;
import com.kata.bankAccount.exception.OperationFailException;



public interface AccountService {
	
	
	  /**
     * Save an Operation.
     *
     * @param Operation the entity to save.
     * @return the persisted entity.
     */
    Account save(Account account);

    /**
     * Get all the Account.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Account> findAll(Pageable pageable);
    
    
    /**
     * Get all the Account.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    List<AccountDto> findAll();


    /**
     * Get the "id" Account.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Account> findOne(Long id);

    /**
     * Delete the "id" Account.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
	
	
	 
	 
	
  
}
