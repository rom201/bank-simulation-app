package com.cydeo.service.impl;

import com.cydeo.emums.AccountStatus;
import com.cydeo.emums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId) {
        //create Account
        Account account = Account.builder().id(UUID.randomUUID())
                .userId(userId)
                .balance(balance)
                .accountType(accountType)
                .creationDate(creationDate).accountStatus(AccountStatus.ACTIVE).build();
        //saving in DB , you need save
        //return for method
        return accountRepository.save(account);


    }

    @Override
    public List<Account> listAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(UUID id) {

        Account account = accountRepository.findById(id);
        account.setAccountStatus(AccountStatus.DELETED);

    }

    @Override
    public void activateAccount(UUID id) {
        Account account = accountRepository.findById(id);
        account.setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public Account retrieveById(UUID id) {

        return accountRepository.findById(id);
    }
}
