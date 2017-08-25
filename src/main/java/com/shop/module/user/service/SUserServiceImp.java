package com.shop.module.user.service;

import com.shop.module.user.dao.SUserRepository;
import com.shop.module.user.domain.SUser;
import com.shop.module.user.domain.SUserResponse;
import com.shop.module.user.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by meg on 7/23/17.
 */

@Service
@Transactional
public class SUserServiceImp implements SUserService {
    private final SUserRepository sUserRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SUserServiceImp(SUserRepository repository, ModelMapper modelMapper){
        this.sUserRepository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SUser findOne(Integer id){
        return (SUser) sUserRepository.findOne(id);
    }

    @Override
    public SUserResponse createUser(SUser user) {
        SUser saved = sUserRepository.save(user);
        return convertToDTO(saved);
    }

    @Override
    public List<SUserResponse> getUsers(int limit) {
        List<SUser> users = sUserRepository.findLastSpecifyLimit(limit);
        return users.stream().map(user ->
                convertToDTO(user)).collect(Collectors.toList());
    }

    private SUserResponse convertToDTO(SUser user) {
        SUserResponse dto = modelMapper.map(user, SUserResponse.class);
        return dto;
    }

    public void validateSUser(int suserId) {
        sUserRepository.findById(suserId).orElseThrow(
                () -> new UserNotFoundException()
        );
    }
}
