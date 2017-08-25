package com.shop.module.user.dao;

import com.shop.module.common.dao.BaseRepository;
import com.shop.module.user.domain.SUser;
import java.util.Optional;

/**
 * Created by meg on 7/23/17.
 */

public interface SUserRepository extends BaseRepository<SUser, Integer> {
    Optional<SUser> findById(Integer productId);

}
