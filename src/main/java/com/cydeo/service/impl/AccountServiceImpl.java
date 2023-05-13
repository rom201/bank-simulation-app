package com.cydeo.service.impl;

import com.cydeo.dto.AccountDTO;
import com.cydeo.emums.AccountStatus;
import com.cydeo.emums.AccountType;
import com.cydeo.entity.Account;
import com.cydeo.mapper.AccountMapper;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;


    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDTO createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId) {
        //create Account
        AccountDTO accountDTO = new AccountDTO();

        //saving in DB , you need save
        //return for method
        return accountRepository.save(accountDTO);


    }

    @Override
    public List<AccountDTO> listAllAccount() {

        List<Account> accountLIst = accountRepository.findAll();
        return accountLIst.stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {

        AccountDTO accountDTO = accountRepository.findById(id);
        accountDTO.setAccountStatus(AccountStatus.DELETED);

    }

    @Override
    public void activateAccount(Long id) {
        AccountDTO accountDTO = accountRepository.findById(id);
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
    }

    @Override
    public AccountDTO retrieveById(Long id) {

        return accountRepository.findById(id);
    }
}
