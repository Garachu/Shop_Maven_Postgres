package com.shop.module.sale.dao;

import com.shop.module.sale.domain.Sale;

import java.util.List;

/**
 * Created by meg on 7/24/17.
 */

public interface SaleRepositoryCustom {

    List<Sale> findSalesWithLimitAndOrdered();

}
