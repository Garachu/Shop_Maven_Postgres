package com.shop.sale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by meg on 7/24/17.
 */


@Transactional
public class SaleRepositoryCustomImp implements SaleRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Sale> findSalesWithLimitAndOrdered(){
            Query query = entityManager.createNativeQuery("SELECT s FROM com.shop.sale.Sale s ORDER BY s.id DESC", Sale.class);
            query.setMaxResults(10);
            return query.getResultList();
        }
}
