package com.shop.account;

import lombok.*;
import javax.validation.constraints.NotNull;

/**
 * Created by meg on 8/14/17.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountInModel {

    @NotNull(message = "accountId May Not Be Null")
    String accountId;

    @NotNull(message = "accountName May Not Be Null")
    String accountName;

    @NotNull(message = "accountContact May Not Be Null")
    String accountContact;

    @NotNull(message = "accountType May Not Be Null")
    String accountType;

    @NotNull(message = "accountNarration May Not Be Null")
    String accountNarration;

//    @NotNull(message = "date May Not Be Null")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    LocalDate date;
    @NotNull(message = "date May Not Be Null")
    String date;

    @NotNull(message = "balance May Not Be Null")
    double balance;

}
