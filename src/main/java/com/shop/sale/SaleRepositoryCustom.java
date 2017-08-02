package com.shop.sale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by meg on 7/24/17.
 */

public interface SaleRepositoryCustom {

    List<Sale> findSalesWithLimitAndOrdered();

}
