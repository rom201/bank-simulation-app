package com.cydeo.service;

import com.cydeo.emums.AccountType;
import com.cydeo.dto.AccountDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    void createNewAccount(AccountDTO accountDTO);

    List<AccountDTO> listAllAccount();

    void deleteAccount(Long id);

    void activateAccount(Long id);

    AccountDTO retrieveById(Long uuid);
}