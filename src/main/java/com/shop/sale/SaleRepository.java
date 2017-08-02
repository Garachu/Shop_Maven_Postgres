package com.shop.sale;

import com.shop.core.BaseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by meg on 7/18/17.
 */

@Repository
public interface SaleRepository extends BaseRepository<Sale, Integer>{
    List<Sale> findByProductId(Integer productId);

    @Query("SELECT s FROM com.shop.sale.Sale s ORDER BY s.id DESC")
    List<Sale> findTop10OrderByTimestampDesc();

}