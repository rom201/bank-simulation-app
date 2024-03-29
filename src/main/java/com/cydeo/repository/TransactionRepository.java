package com.cydeo.repository;

import com.cydeo.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    public static List<Transaction> transactionList = new ArrayList<>();
    public Transaction save (Transaction transaction){
        transactionList.add(transaction);
        return transaction;

    }


    public List<Transaction> findAll() {
        return transactionList;
    }
// all work with DB and getting data will be created in repository
    public List<Transaction> findLast10Transactions() {

        return transactionList.stream()
                .sorted(Comparator.comparing(Transaction::getCreationDate).reversed())
                .limit(10)
                .collect(Collectors.toList());

    }

    public List<Transaction> findTransactonListById(UUID id) {

        return transactionList.stream()
                .filter(p->p.getSender().equals(id) || p.getReceiver().equals(id))
                .collect(Collectors.toList());

    }
}
