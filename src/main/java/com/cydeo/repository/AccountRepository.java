package com.cydeo.repository;

import com.cydeo.dto.AccountDTO;
import com.cydeo.entity.Account;
import com.cydeo.exception.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {





}
