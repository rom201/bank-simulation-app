package com.cydeo.service.impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.dto.TransactionDTO;
import com.cydeo.emums.AccountType;
import com.cydeo.entity.Transaction;
import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.exception.UnderConstructionException;
import com.cydeo.mapper.TransactionMapper;
import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.TransactionRepository;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionServiceImpl implements TransactionService {

    @Value("${under_construction}")
    private boolean underConstruction;
//    private final AccountRepository accountRepository;


    private final AccountService accountService;
    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;


    public TransactionServiceImpl(AccountService accountService, TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTO makeTransfer(AccountDTO sender, AccountDTO receiver, BigDecimal amount, Date creationDate, String message) {

        if(!underConstruction) {
            validateAccount(sender, receiver);
            checkAccountOwnerShip(sender, receiver);
            executeBalanceAndUpdateIfRequired(amount, sender, receiver);
        /*
        after validations, we need create transfer object
         */
            TransactionDTO transactionDTO = new TransactionDTO();

            return transactionRepository.save(transactionDTO);
        }else{
            throw new UnderConstructionException("App is under construction, try again later.");
        }

    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, AccountDTO sender, AccountDTO receiver) {
        if(checkSenderBalance(sender, amount)){
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else{
            throw new BalanceNotSufficientException("Balance is not enough for this transfer.");
        }

    }

    private boolean checkSenderBalance(AccountDTO sender, BigDecimal amount) {
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO)>=0;
    }

    private void checkAccountOwnerShip(AccountDTO sender, AccountDTO receiver) {

        if((sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING))
            && !sender.getUserId().equals(receiver.getUserId())){
            throw new AccountOwnershipException("Since you are using a savings accounts, sender and receiver userId mast be the same");

        }

    }

    private void validateAccount(AccountDTO sender, AccountDTO receiver) {
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

    private void findAccountById(Long id) {
        accountRepository.findById(id);


    }

    @Override
    public List<TransactionDTO> findAllTransaction() {

        return transactionRepository.findAll().stream().map(transactionMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> last10transactions() {


        return transactionRepository.findLast10Transactions();
    }

    @Override
    public List<TransactionDTO> listTransactionListBiID(Long id) {
        return transactionRepository.findTransactionListById(id);
    }
}
