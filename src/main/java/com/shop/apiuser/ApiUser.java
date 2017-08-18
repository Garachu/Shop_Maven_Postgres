package com.shop.apiuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

/**
 * Created by meg on 8/2/17.
 */

@Entity
@Table(name = "api_user", catalog = "shop_home_test", schema = "base")
public class ApiUser {

    public static PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private String username;
    @JsonIgnore
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private String emailId;
    @JsonIgnore
    private String roles;

    public ApiUser() {
    }

    public ApiUser(String username, String password, boolean enabled,boolean accountNonExpired,boolean accountNonLocked,boolean credentialsNonExpired, String emailId) {
        this.username = username;
        setPassword(password);
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.emailId = emailId;
    }

    public ApiUser(String username, String password, boolean enabled,boolean accountNonExpired,boolean accountNonLocked,boolean credentialsNonExpired, String roles,String emailId) {
        this.username = username;
        setPassword(password);
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.emailId = emailId;
        this.roles = roles;
    }

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 45)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 80)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password)
    {
       //this.password = PASSWORD_ENCODER.encode(password);
      this.password = password;
    }

    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Column(name = "accountNonExpired", nullable = false)
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Column(name = "accountNonLocked", nullable = false)
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Column(name = "credentialsNonExpired", nullable = false)
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Column(name = "emailId", nullable = false)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name ="roles", nullable = false)
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
