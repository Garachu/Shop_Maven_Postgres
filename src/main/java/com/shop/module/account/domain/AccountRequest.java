package com.shop.module.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by meg on 8/14/17.
 */


/*
        Data Transfer Object.
        object received by the service layer and serialised to entity.
        A best practice is to structure the Dto as an immutable object
*/

@Getter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotNull(message = "date May Not Be Null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    //@NotNull(message = "accountId May Not Be Null")
    @Size(min = 8, max = 20, message = "accountId should be within 8 to 10 characters")
    private String accountId;

    //@NotNull(message = "accountName May Not Be Null")
    @Size(min = 4, max = 16, message = "accountName should be within 4 to 16 characters")
    private String accountName;

    //@NotNull(message = "accountContact May Not Be Null")
    @Size(min = 10, max = 14, message = "accountContact should be within 10 to 14 characters")
    private String accountContact;

    //@NotNull(message = "accountType May Not Be Null")
    @Size(min = 4, max = 10, message = "accountType should be within 4 to 10 characters")
    private String accountType;

    //@NotNull(message = "accountNarration May Not Be Null")
    @Size(min = 4, max = 60, message = "accountNarration should be within 4 to 60 characters")
    private String accountNarration;

    @NotNull(message = "balance May Not Be Null")
    @Digits(integer = 6, fraction = 0, message = "The value of balance can not be more than 6 digits")
    private double balance;

    public static Account convertToEntity(AccountRequest dto){
        //Account account = modelMapper.map(dto, Account.class);
        Account account = new Account();
        Date date = Date.from(dto.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        account.setAccountId(dto.getAccountId());
        account.setAccountName(dto.getAccountName());
        account.setAccountContact(dto.getAccountContact());
        account.setAccountType(dto.getAccountType());
        account.setAccountNarration(dto.getAccountNarration());
        account.setFirstModifiedDate(date);
        account.setLastModifiedDate(date);
        account.setInitialBalance(dto.getBalance());
        return account;
    }

}
