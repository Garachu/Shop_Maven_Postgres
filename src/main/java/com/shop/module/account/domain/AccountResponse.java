package com.shop.module.account.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by meg on 8/18/17.
 */

/*
        Data Transfer Object.
        object returned by the service layer and serialised to Json.
        A best practice is to structure the Dto as an immutable object
*/

@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
//Redis serializer (JdkSerializationRedisSerializer) uses Java based serialization which requires the classes to be Serializable.
public class AccountResponse extends JdkSerializationRedisSerializer implements Serializable{

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd");

    private String accountId;
    private String accountName;
    private String accountType;
    private String accountNarration;
    private String firstModifiedDate;
    private String lastModifiedDate;
    private double initialBalance;
    private double balance;

    public void setFirstModifiedDate(Date date, String timezone) {
        //dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.firstModifiedDate = dateFormat.format(date);
    }

    public void setLastModifiedDate(Date date, String timezone) {
        //dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
        this.lastModifiedDate = dateFormat.format(date);
    }

    public static AccountResponse convertoDTO(Account account) {
        //AccountResponse accountDTO = modelMapper.map(account, AccountResponse.class);
        AccountResponse accountDTO = new AccountResponse();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setAccountName(account.getAccountName());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setAccountNarration(account.getAccountNarration());
        accountDTO.setFirstModifiedDate(account.getFirstModifiedDate(), "");
        accountDTO.setLastModifiedDate(account.getLastModifiedDate(), "");
        accountDTO.setInitialBalance(account.getInitialBalance());
        accountDTO.setBalance(account.getBalance());
        return accountDTO;
    }

}
