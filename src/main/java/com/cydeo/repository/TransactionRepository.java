package com.cydeo.repository;

import com.cydeo.dto.TransactionDTO;
import com.cydeo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query(value  ="SELECT * FROM transactions ORDER BY creation_date DESC LIMIT 10", nativeQuery = true)
    List<Transaction> findLast10Transactions ();


    @Query("select t from Transaction t where t.sender.id = ?1 OR t.receiver.id = ?1")
    List<Transaction> findTransactionListById(Long id);










}
