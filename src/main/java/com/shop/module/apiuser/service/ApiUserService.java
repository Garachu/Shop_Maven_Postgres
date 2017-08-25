package com.shop.module.apiuser.service;

import com.shop.module.apiuser.domain.ApiUserRequest;
import com.shop.module.apiuser.domain.ApiUserResponse;

import java.util.List;

/**
 * Created by meg on 8/20/17.
 */

public interface ApiUserService {

    ApiUserResponse createUser(ApiUserRequest userRequest);
    List<ApiUserResponse> findAll();
    ApiUserResponse findByUsername(String username);

}
