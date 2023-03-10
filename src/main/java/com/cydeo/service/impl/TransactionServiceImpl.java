package com.cydeo.service.impl;

import com.cydeo.emums.AccountType;
import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.TransactionRepository;
import com.cydeo.service.TransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) {

        /*
        -if sender is null?
        -if sender and receiver is same acaount
        -if sender has balance
        -if anny condition (wrong) stop transaction

         */
        validateAccount(sender, receiver);
        checkAccountOwnerShip(sender, receiver);
        executeBalanceAndUpdateIfRequired(amount, sender, receiver);
        /*
        after validations we need create transfer object
         */
        Transaction transaction = Transaction.builder().amount(amount).sender(sender.getId())
                .receiver(receiver.getId()).creationDate(creationDate).message(message).build();

        return transactionRepository.save(transaction);

    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) {
        if(checkSenderBalance(sender, amount)){
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else{
            throw new BalanceNotSufficientException("Balance is not enough for this transfer.");
        }

    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO)>=0;
    }

    private void checkAccountOwnerShip(Account sender, Account receiver) {

        if((sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING))
            && !sender.getId().equals(receiver.getId())){
            throw new AccountOwnershipException("Since you are using a savings accounts, sender and receiver userId mast be the same");

        }

    }

    private void validateAccount(Account sender, Account receiver) {
        //if any null
        if(sender==null || receiver==null){
            throw new BadRequestException("Sender or Receiver cannot be null");
        }
        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account needs to be different than receiver");
        }

        //verify if we have sender and receiver in DB
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
