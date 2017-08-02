package com.shop.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by meg on 7/23/17.
 */

@Service
@Transactional
public class SUserServiceImp implements SUserService{
    private final SUserRepository repository;

    @Autowired
    public SUserServiceImp(SUserRepository repository){
        this.repository = repository;
    }

    @Override
    public SUser findOne(Integer id) {
        return (SUser) repository.findOne(id);
    }

    @Override
    public void validateSUser(int suserId) {
        repository.findById(suserId).orElseThrow(
                () -> new UserNotFoundException()
        );
    }
}
