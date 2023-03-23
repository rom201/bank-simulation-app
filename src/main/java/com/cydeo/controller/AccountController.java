package com.cydeo.controller;

import com.cydeo.emums.AccountType;
import com.cydeo.model.Account;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    public String getCreateForm(Model model){

        //empty account object provided
        model.addAttribute("account", Account.builder().build());
        //account type enum needs to fill dropdown
        model.addAttribute("accountTypes", AccountType.values());

        return "account/create-account";
    }

    //create method to capture information from UI,

    @PostMapping("/create")
    public String createAccount(@ModelAttribute("account") Account account){
//print them on the console.
        //trigger createAccount method, create the account based on user input.
        accountService.createNewAccount(account.getBalance(), new Date(), account.getAccountType(), account.getUserId());

        return "redirect:/index"; //just go to next end point and do not need repeat
    }


    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") UUID id){

        accountService.deleteAccount(id);

        return "redirect:/index";

    }

    @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable("id") UUID id){

        accountService.activateAccount(id);

        return "redirect:/index";

    }



}
