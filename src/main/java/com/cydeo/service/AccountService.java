package com.cydeo.service;

import com.cydeo.emums.AccountType;
import com.cydeo.model.Account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId);

    List<Account> listAccount();

}
