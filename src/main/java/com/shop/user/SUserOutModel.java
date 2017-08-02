package com.shop.user;

/**
 * Created by meg on 7/28/17.
 */

public class SUserOutModel {

    private String fullname;
    private String username;

    public SUserOutModel(String fullname, String username) {
        this.fullname = fullname;
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
