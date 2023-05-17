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
    public void createNewAccount(AccountDTO accountDTO) {

        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        accountDTO.setCreationDate(new Date());

        accountRepository.save(accountMapper.convertToEntity(accountDTO));

    }


    @Override
    public List<AccountDTO> listAllAccount() {

        List<Account> accountLIst = accountRepository.findAll();
        return accountLIst.stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {


        Account account = accountRepository.findById(id).get();

        account.setAccountStatus(AccountStatus.DELETED);

        accountRepository.save(account);

    }

    @Override
    public void activateAccount(Long id) {

        Account account = accountRepository.findById(id).get();

        account.setAccountStatus(AccountStatus.ACTIVE);

        accountRepository.save(account);

    }

    @Override
    public AccountDTO retrieveById(Long id) {

        return accountMapper.convertToDTO(accountRepository.findById(id).get());
    }

    @Override
    public List<AccountDTO> listAllActiveAccounts() {

        List<Account> listActiveAccounts = accountRepository.findAllByAccountStatus(AccountStatus.ACTIVE);
        return listActiveAccounts.stream().map(accountMapper::convertToDTO).collect(Collectors.toList());
    }


}
