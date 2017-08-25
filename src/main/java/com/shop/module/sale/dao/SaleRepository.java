package com.shop.module.sale.dao;

import com.shop.module.common.dao.BaseRepository;
import com.shop.module.sale.domain.Sale;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by meg on 7/18/17.
 */

@Repository
public interface SaleRepository extends BaseRepository<Sale, Integer> {

    List<Sale> findByProductId(Integer productId);
    List findSaleByTimestampIsAfter(@Param("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate timestamp);
    //List findSaleBy


//    List findByPublishedDateAfter(@Param("publishedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedDate);
//
//    List findByTitleContainsAndPublishedDateAfter(@Param("keyword") String keyword,
//                                                  @Param("publishedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishedDate);


}