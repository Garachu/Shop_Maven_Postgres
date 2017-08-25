package com.shop.module.apiuser.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by meg on 8/2/17.
 */

@NoArgsConstructor
@Getter //Need the getters at the CustomerUserDetailsService
@Setter(AccessLevel.PACKAGE)
@Entity
@Table(name = "api_user", catalog = "shop_home_test", schema = "base")
public class ApiUser implements Serializable{

    public static PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 80)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "accountNonExpired", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked", nullable = false)
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired", nullable = false)
    private boolean credentialsNonExpired;

    @Column(name = "emailId", nullable = false)
    private String emailId;

    @Column(name ="roles", nullable = false)
    private String roles;

    public void setPassword(String password)
    {
       this.password = PASSWORD_ENCODER.encode(password);
    }

}
