package com.cydeo.service.impl;

import com.cydeo.exception.BadRequestException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    final private AccountRepository accountRepository;

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate) {

        /*
        -if sender is null?
        -if sender and receiver is same acaount
        -if sender has balance
        -if anny condition (wrong) stop transaction

         */
        validateAccount(sender, receiver);





        return null;
    }

    private void validateAccount(Account sender, Account receiver) {
        //if any null
        if(sender==null || receiver==null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }
        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different than receiver");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());




    }

    private void findAccountById(UUID id) {
        accountRepository.findById(id);


    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
