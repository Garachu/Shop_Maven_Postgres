package com.shop.module.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by meg on 7/28/17.
 */

/*
        Data Transfer Object.
        object returned by the service layer and serialised to Json.
        A best practice is to structure the Dto as an immutable object
*/

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class SUserResponse {

    private String fullname;
    private String username;

    public SUserResponse(String fullname, String username) {
        this.fullname = fullname;
        this.username = username;
    }


}
