package com.shop.sale;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by meg on 7/18/17.
 */

@Service
@Transactional
class SaleServiceImp implements SaleService{

    private final SaleRepository saleRepository;


   public SaleServiceImp(SaleRepository saleRepository){
       this.saleRepository = saleRepository;
   }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale findOne(Integer id) {
        return saleRepository.findOne(id);
    }

    @Override
    public void delete(Integer id) {
        saleRepository.delete(id);
    }

    @Override
    public List<Sale> findByProductId(Integer productId) {
        return saleRepository.findByProductId(productId);
    }



}
