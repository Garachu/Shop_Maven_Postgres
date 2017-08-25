package com.shop.module.apiuser.service;

import com.shop.module.apiuser.dao.ApiUserRepository;
import com.shop.module.apiuser.domain.ApiUser;
import com.shop.module.apiuser.domain.ApiUserRequest;
import com.shop.module.apiuser.domain.ApiUserResponse;
import com.shop.module.apiuser.exception.ApiUserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by meg on 8/23/17.
 */

@Service
@Transactional
@Slf4j
public class ApiUserServiceImpl implements ApiUserService{

    private final ApiUserRepository apiUserRepository;

    @Autowired
    public ApiUserServiceImpl(ApiUserRepository apiUserRepository) {
        this.apiUserRepository = apiUserRepository;
    }

    @Override
    public ApiUserResponse createUser(ApiUserRequest userRequest) {
        ApiUser apiUser = ApiUserRequest.convertToEntity(userRequest);
        ApiUser saved = apiUserRepository.save(apiUser);
        ApiUserResponse apiUserResponse = ApiUserResponse.convertToDTO(saved);
        return ApiUserResponse.convertToDTO(saved);
    }

    @Override
    public List<ApiUserResponse> findAll() {
        List<ApiUser> apiUsers = apiUserRepository.findAll();
        return apiUsers.stream().map(user ->
                ApiUserResponse.convertToDTO(user)).collect(Collectors.toList());
    }

    @Override
    public ApiUserResponse findByUsername(String username) {
        ApiUser apiUser = apiUserRepository.findByUsername(username.trim()).orElseThrow(
        () ->  new ApiUserNotFoundException());
           return ApiUserResponse.convertToDTO(apiUser);
    }

//    ApiUser convertToEntity(ApiUserRequest userRequest){
//        ApiUser apiUser = modelMapper.map(userRequest, ApiUser.class);
//        apiUser.setPassword(userRequest.getPassword());
//        return apiUser;
//    }
//
//    ApiUserResponse convertToDTO(ApiUser apiUser){
//        ApiUserResponse apiUserResponse = modelMapper.map(apiUser, ApiUserResponse.class);
//        return  apiUserResponse;
//
//    }
}
