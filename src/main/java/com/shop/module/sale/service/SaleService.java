package com.shop.module.sale.service;

import com.shop.module.sale.domain.Sale;

import java.util.List;

/**
 * Created by meg on 7/18/17.
 */
public interface SaleService {
    List<Sale> findAll();
    Sale save(Sale product);
    Sale findOne(Integer id);
    void delete(Integer id);
    List<Sale> findByProductId(Integer productId);
}
