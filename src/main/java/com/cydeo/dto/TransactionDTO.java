package com.cydeo.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    @NotNull
    private AccountDTO sender;
    @NotNull
    private AccountDTO receiver;

    @NotNull
    @Positive
    private BigDecimal amount;


    @Size(min = 2,max = 250)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
//    @NotNull
//    @NotBlank
    @NotEmpty
    private String message;

    private Date creationDate;

}
