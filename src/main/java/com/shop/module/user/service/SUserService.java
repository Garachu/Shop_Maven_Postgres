package com.shop.module.user.service;

import com.shop.module.user.domain.SUser;
import com.shop.module.user.domain.SUserResponse;

import java.util.List;

/**
 * Created by meg on 7/23/17.
 */
public interface SUserService {

    SUser findOne(Integer id);
    SUserResponse createUser (SUser user);
    List<SUserResponse> getUsers(int limit);

}
