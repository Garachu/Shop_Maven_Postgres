package com.shop.sale;

import com.shop.core.Result;
import com.shop.core.errorhandling.RestPrecondition;
import com.shop.product.Product;
import com.shop.product.ProductService;
import com.shop.user.SUser;
import com.shop.user.SUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by meg on 7/18/17.
 */

@RestController
@RequestMapping("/sales")
class SaleController {

    private final SaleService saleService;

    private final ProductService productService;

    private final SUserService sUserService;

    private final SaleRepository saleRepository;

    @Autowired
    public SaleController(SaleService saleService, ProductService productService, SUserService sUserService, SaleRepository saleRepository) {
        this.saleService = saleService;
        this.productService = productService;
        this.sUserService = sUserService;
        this.saleRepository = saleRepository;
    }

    //GET         shop/sales
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> findAll() {
        List<SaleOutModel>  list =   saleRepository.findLastSpecifyLimit(20)
                .stream().map(s ->
                        new SaleOutModel(s.getProduct().getLabel(), s.getProduct().getDescription(), s.getQuantity(), s.getSp() * s.getQuantity(), s.getQuantity() * (s.getSp() - s.getProduct().getBp()), s.getTimestamp(), s.getUser().getUsername()))
                .collect(Collectors.toList());

        Result result = new Result();
        if(list.isEmpty()){
            result.setMessage("No Sales Found");
        }else{
            result.setMessage("success");
        }
        result.setResult(list);
        return ResponseEntity.ok(result);
    }

    //GET         shop/sales/{start}/{end}
    @RequestMapping(value = "/{start}/{end}", method = RequestMethod.GET)
    @ResponseBody
    public List<SaleOutModel> findRange(@PathVariable("start") int start, @PathVariable("end") int end) {
        return  saleRepository.findAll(new int[]{start, end})
                .stream().map(s ->
                new SaleOutModel(s.getProduct().getLabel(), s.getProduct().getDescription(), s.getQuantity(), s.getSp() * s.getQuantity(), s.getQuantity() * (s.getSp() - s.getProduct().getBp()), s.getTimestamp(), s.getUser().getUsername()))
                .collect(Collectors.toList());
    }


    //GET         shop/sales/{id}
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SaleOutModel findOne(@PathVariable("id") int id) {
        Sale s = RestPrecondition.checkSaleFound(saleService.findOne(id));
        return new SaleOutModel(s.getProduct().getLabel(), s.getProduct().getDescription(), s.getQuantity(), s.getSp() * s.getQuantity(), s.getQuantity() * (s.getSp() - s.getProduct().getBp()), s.getTimestamp(), s.getUser().getUsername());
    }


    //POST          shop/sales
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SaleOutModel create(@Valid @RequestBody SaleInModel saleInModel) {
        Product product = RestPrecondition.checkProductFound(productService.findOne(saleInModel.getProductId()));
        SUser user = RestPrecondition.checkUserFound(sUserService.findOne(saleInModel.getUserId()));
        int profit = saleInModel.getSp() - product.getBp();
        Sale s = saleService.save(new Sale(saleInModel.getSp(), saleInModel.getQuantity(), new Date(), product, profit, user));
        return new SaleOutModel(s.getProduct().getLabel(), s.getProduct().getDescription(), s.getQuantity(), s.getSp() * s.getQuantity(), s.getQuantity() * (s.getSp() - s.getProduct().getBp()), s.getTimestamp(), s.getUser().getUsername());
    }


}
