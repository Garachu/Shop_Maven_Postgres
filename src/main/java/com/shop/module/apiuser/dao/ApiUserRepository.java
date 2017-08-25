package com.shop.module.apiuser.dao;

import com.shop.module.apiuser.domain.ApiUser;
import com.shop.module.common.dao.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by meg on 8/2/17.
 */

@RepositoryRestResource(exported = false)
public interface ApiUserRepository extends BaseRepository<ApiUser, String> {

    //These method will be implemented dynamically by Spring Data
    Optional<ApiUser> findByUsername(@Param("username") String username);

    //These method will be implemented dynamically by Spring Data
    //SELECT U FROM ApiUser U WHERE U.username = ? OR U.emailId = ?
    Optional<ApiUser> findApiUserByUsernameOrEmailId(
        @Param("username") String username,
        @Param("emailId") String emailId
    );
}
