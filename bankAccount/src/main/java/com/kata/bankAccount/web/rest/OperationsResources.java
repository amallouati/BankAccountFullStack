package com.kata.bankAccount.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kata.bankAccount.dto.AccountDto;
import com.kata.bankAccount.dto.OperationDto;
import com.kata.bankAccount.exception.OperationFailException;
import com.kata.bankAccount.services.OperationService;

@RestController
@RequestMapping("/api/operation/")
@CrossOrigin
public class OperationsResources {
	
	 private final OperationService operationService;
	 
	 public OperationsResources(OperationService operationService) {
		 this.operationService=operationService;
	 }
	
	 @GetMapping("{accountId}/all")
	    public List<OperationDto> getAllOperations(@PathVariable Long accountId)  {
	        return operationService.findAllByAccountId(accountId);
	    }
	    
	 
	    @PostMapping(value = "{accountId}/add")
	    public AccountDto addOperation(@PathVariable Long accountId, @RequestBody OperationDto operationdto) throws OperationFailException {
	        return operationService.doTransaction(accountId, operationdto);
	    }   

}
