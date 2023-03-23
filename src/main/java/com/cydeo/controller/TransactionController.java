package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransactionController {

    @GetMapping ("/make-transfer")
    public String getMakeTransfer(){

        //empty transaction object
        //we need all accounts to provide them as sender and receiver
        // need list of transaction 10 for table
        //sme logic for endpoint


        return "transaction/make-transfer";
    }





}
