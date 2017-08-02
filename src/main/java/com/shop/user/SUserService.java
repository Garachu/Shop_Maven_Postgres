package com.shop.user;

/**
 * Created by meg on 7/23/17.
 */
public interface SUserService {

    SUser findOne(Integer id);
    void validateSUser(int suserId);

}
