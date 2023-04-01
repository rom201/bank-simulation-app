package com.cydeo.controller;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class TransactionController {

    private final AccountService accountService;
    private final TransactionService transactionService;


    public TransactionController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @GetMapping ("/make-transfer")
    public String getMakeTransfer(Model model){

        //empty transaction object
        model.addAttribute("transaction", Transaction.builder().build());

        //we need all accounts to provide them as sender and receiver
        model.addAttribute("accounts", accountService.listAllAccount());

        // need list of transaction 10 for table
        model.addAttribute("lastTransactions", transactionService.last10transactions());

        //sme logic for endpoint
        return "transaction/make-transfer";
    }

    @PostMapping("/transfer")
    public String postMakeTransfer (@ModelAttribute ("transaction") Transaction transaction){

        Account sender = accountService.retrieveById(transaction.getSender());
        Account receiver = accountService.retrieveById(transaction.getReceiver());

        transactionService.makeTransfer(sender,receiver, transaction.getAmount(), new Date(), transaction.getMessage());

        return "redirect:/make-transfer";

    }





}
