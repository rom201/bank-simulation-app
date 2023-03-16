package com.cydeo.controller;

import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/index")
    public String getIndex(Model model){
        model.addAttribute("accountList", accountService.listAllAccount());
        return "account/index";
    }


    @GetMapping("/create-form")
    public String getCreateForm(){

        //empty account object provided
        //account type enum needs to fill dropdown

        return "account/create-account";
    }

    //create method to capture information from UI,
    //print them on the console.
    //trigger createAccount method, create the account based on user input.



}
