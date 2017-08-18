package com.shop.accountTransaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by meg on 8/16/17.
 */

@Getter
@Setter
@AllArgsConstructor
public class ATransactionInModel {

    @NotNull(message = "accountId May Not Be Null")
    String accountId;

    @NotNull(message = "accountName May Not Be Null")
    String transactionType;

    @NotNull(message = "accountContact May Not Be Null")
    String narration;

    @NotNull(message = "accountType May Not Be Null")
    double amount;

    @NotNull(message = "date May Not Be Null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
}
