package com.shop.apiuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by meg on 8/2/17.
 */

@RepositoryRestResource(exported = false)
public interface ApiUserRepository extends JpaRepository<ApiUser, Long>{
    Optional<ApiUser> findByUsername(String username);
}
