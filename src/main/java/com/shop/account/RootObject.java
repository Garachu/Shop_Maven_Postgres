package com.shop.account;

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
    private List<AccountInModel> accounts;

    public RootObject() {
    }




}
