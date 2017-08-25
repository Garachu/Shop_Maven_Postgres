package com.shop.module.account.domain;

import com.shop.module.account.domain.AccountRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by meg on 8/18/17.
 */

@Getter
@Setter
public class RootObject {

    @NotNull(message = "invalid object")
    private List<AccountRequest> accounts;

    public RootObject() {
    }




}
