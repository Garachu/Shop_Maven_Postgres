package com.shop.user;

import com.shop.core.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by meg on 7/23/17.
 */

public interface SUserRepository extends BaseRepository<SUser, Integer> {
    Optional<SUser> findById(Integer productId);
}
