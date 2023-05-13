package com.cydeo.repository;

import com.cydeo.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TransactionRepository {

    public static List<TransactionDTO> transactionDTOList = new ArrayList<>();
    public TransactionDTO save (TransactionDTO transactionDTO){
        transactionDTOList.add(transactionDTO);
        return transactionDTO;

    }


    public List<TransactionDTO> findAll() {
        return transactionDTOList;
    }
// all work with DB and getting data will be created in repository
    public List<TransactionDTO> findLast10Transactions() {

        return transactionDTOList.stream()
                .sorted(Comparator.comparing(TransactionDTO::getCreationDate).reversed())
                .limit(10)
                .collect(Collectors.toList());

    }

    public List<TransactionDTO> findTransactionListById(Long id) {

        return transactionDTOList.stream()
                .filter(p->p.getSender().equals(id) || p.getReceiver().equals(id))
                .collect(Collectors.toList());

    }
}
