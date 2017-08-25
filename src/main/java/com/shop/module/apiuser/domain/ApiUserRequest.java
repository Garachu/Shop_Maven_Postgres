package com.shop.module.apiuser.domain;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by meg on 8/23/17.
 */

/*
        Data Transfer Object.
        object received by the service layer and serialised to entity.
        A best practice is to structure the Dto as an immutable object
*/

@Getter(AccessLevel.PRIVATE)
//@Setter
@AllArgsConstructor
public class ApiUserRequest {

    @NotNull(message = "Username must not be null")
    @Size(min=4, message = "Username minimum length is 4")
    private String username;

    @NotNull(message = "Password must not be null")
    @Size(min=8, message = "Password minimum length is 8")
    private String password;

    private boolean enabled;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    @NotNull(message = "emailId must not be null")
    //@Size(min=12, message = "emailId minimum length is 12")
    @Email(message = "Invalid email id")
    private String emailId;

    @NotNull(message = "roles must not be null")
    @Size(min=4, max=60, message = "roles minimum length is 4")
    private String roles;

    //This is what is used when creating a entity instance
    public ApiUserRequest(){
        this.accountNonExpired = Boolean.TRUE;
        this.accountNonLocked = Boolean.TRUE;
        this.credentialsNonExpired = Boolean.TRUE;
        this.enabled = Boolean.TRUE;
    }

    public static ApiUser convertToEntity(ApiUserRequest userRequest){
        ApiUser apiUser = new ApiUser();
        apiUser.setUsername(userRequest.getUsername());
        apiUser.setPassword(userRequest.getPassword());
        apiUser.setEnabled(userRequest.isEnabled());
        apiUser.setAccountNonExpired(userRequest.accountNonExpired);
        apiUser.setAccountNonLocked(userRequest.isAccountNonLocked());
        apiUser.setCredentialsNonExpired(userRequest.isCredentialsNonExpired());
        apiUser.setEmailId(userRequest.getEmailId());
        apiUser.setRoles(userRequest.getRoles());
        return apiUser;
    }


}
