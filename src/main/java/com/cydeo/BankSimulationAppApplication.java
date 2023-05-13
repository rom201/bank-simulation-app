package com.cydeo;

import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankSimulationAppApplication {

    public static void main(String[] args) {
//        SpringApplication.run(BankSimulationAppApplication.class, args);
        ApplicationContext container = SpringApplication.run(BankSimulationAppApplication.class, args);

        AccountService accountService = container.getBean(AccountService.class);
        TransactionService transactionService = container.getBean(TransactionService.class);
//
//        //create 2 accounts
//        Account sender = accountService.createNewAccount(BigDecimal.valueOf(70),new Date(), AccountType.CHECKING, 1L);
//        Account receiver = accountService.createNewAccount(BigDecimal.valueOf(50),new Date(), AccountType.CHECKING, 2L);
//        Account receiver1 = accountService.createNewAccount(BigDecimal.valueOf(150),new Date(), AccountType.CHECKING, 3L);
//        Account receiver2 = accountService.createNewAccount(BigDecimal.valueOf(250),new Date(), AccountType.SAVING, 4L);
//        Account receiver2 = null;


//        accountService.listAllAccount().forEach(System.out::println);
//        transactionService.makeTransfer(sender, receiver, new BigDecimal(40),new Date(), "Transaction 1");
//
//
//        System.out.println(transactionService.findAllTransaction().get(0));
//
//        accountService.listAllAccount().forEach(System.out::println);
//        transactionService.makeTransfer(sender, receiver,new BigDecimal(40),new Date(), "Transaction 2");


    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
