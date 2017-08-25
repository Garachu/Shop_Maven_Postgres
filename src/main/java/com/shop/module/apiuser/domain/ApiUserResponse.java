package com.shop.module.apiuser.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by meg on 8/23/17.
 */

/*
        Data Transfer Object.
        object returned by the service layer and serialised to Json.
        A best practice is to structure the Dto as an immutable object
*/

@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public class ApiUserResponse {

    private String username;
    private String emailId;
    private String roles;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    public static ApiUserResponse convertToDTO(ApiUser apiUser){
        ApiUserResponse apiUserResponse = new ApiUserResponse();
        apiUserResponse.setUsername(apiUser.getUsername());
        apiUserResponse.setEmailId(apiUser.getEmailId());
        apiUserResponse.setRoles(apiUser.getRoles());
        apiUserResponse.setAccountNonExpired(apiUser.isAccountNonExpired());
        apiUserResponse.setAccountNonLocked(apiUser.isAccountNonLocked());
        apiUserResponse.setCredentialsNonExpired(apiUser.isCredentialsNonExpired());
        return  apiUserResponse;

    }



}
