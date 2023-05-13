package com.cydeo.controller;

import com.cydeo.dto.AccountDTO;
import com.cydeo.emums.AccountType;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

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
        model.addAttribute("account", new AccountDTO());
        //account type enum needs to fill dropdown
        model.addAttribute("accountTypes", AccountType.values());

        return "account/create-account";
    }

    //create method to capture information from UI,

    @PostMapping("/create")
    public String createAccount(@Valid @ModelAttribute("account") AccountDTO accountDTO, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){

            model.addAttribute("accountTypes", AccountType.values());
            return "account/create-account";
        }
        accountService.createNewAccount(accountDTO);
        return "redirect:/index"; //just go to next end point and do not need repeat
    }


    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long id){

        accountService.deleteAccount(id);

        return "redirect:/index";

    }

    @GetMapping("/activate/{id}")
    public String activateAccount(@PathVariable("id") Long id){

        accountService.activateAccount(id);

        return "redirect:/index";

    }



}
