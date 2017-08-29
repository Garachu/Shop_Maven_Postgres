package com.shop.module.sale.rest;

import com.shop.module.common.domain.Result;
import com.shop.module.common.util.RestPrecondition;
import com.shop.module.product.domain.Product;
import com.shop.module.product.service.ProductService;
import com.shop.module.sale.domain.SaleResponse;
import com.shop.module.sale.dao.SaleRepository;
import com.shop.module.sale.service.SaleService;
import com.shop.module.sale.domain.Sale;
import com.shop.module.sale.domain.SaleRequest;
import com.shop.module.user.domain.SUser;
import com.shop.module.user.service.SUserService;
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
class SaleRestController {

    private final SaleService saleService;

    private final ProductService productService;

    private final SUserService sUserService;

    private final SaleRepository saleRepository;

    @Autowired
    public SaleRestController(SaleService saleService, ProductService productService, SUserService sUserService, SaleRepository saleRepository) {
        this.saleService = saleService;
        this.productService = productService;
        this.sUserService = sUserService;
        this.saleRepository = saleRepository;
    }

//    //GET         shop/sales
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<?> findAll() {
//        List<SaleResponse>  list =   saleRepository.findLastSpecifyLimit(20)
//                .stream().map(s ->
//                        new SaleResponse(s.getProduct().getLabel(), s.getProduct().getDescription(), s.getQuantity(), s.getSp() * s.getQuantity(), s.getQuantity() * (s.getSp() - s.getProduct().getBp()), s.getTimestamp(), s.getUser().getUsername()))
//                .collect(Collectors.toList());
//
//        Result result = new Result();
//        if(list.isEmpty()){
//            result.setMessage("No Sales Found");
//        }else{
//            result.setMessage("success");
//        }
//        result.setResult(list);
//        return ResponseEntity.ok(result);
//    }

//    //GET         shop/sales/{start}/{end}
//    @RequestMapping(value = "/{start}/{end}", method = RequestMethod.GET)
//    @ResponseBody
//    public List<SaleResponse> findRange(@PathVariable("start") int start, @PathVariable("end") int end) {
//        return  saleRepository.findAll(new int[]{start, end})
//                .stream().map(s ->
//                new SaleResponse(s.getProduct().getLabel(), s.getProduct().getDescription(), s.getQuantity(), s.getSp() * s.getQuantity(), s.getQuantity() * (s.getSp() - s.getProduct().getBp()), s.getTimestamp(), s.getUser().getUsername()))
//                .collect(Collectors.toList());
//    }


//    //GET         shop/sales/{id}
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public SaleResponse findOne(@PathVariable("id") int id) {
//        Sale s = RestPrecondition.checkSaleFound(saleService.findOne(id));
//        return new SaleResponse(s.getProduct().getLabel(), s.getProduct().getDescription(), s.getQuantity(), s.getSp() * s.getQuantity(), s.getQuantity() * (s.getSp() - s.getProduct().getBp()), s.getTimestamp(), s.getUser().getUsername());
//    }


//    //POST          shop/sales
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public SaleResponse create(@Valid @RequestBody SaleRequest saleInModel) {
//        Product product = RestPrecondition.checkProductFound(productService.findOne(saleInModel.getProductId()));
//        SUser user = RestPrecondition.checkUserFound(sUserService.findOne(saleInModel.getUserId()));
//        int profit = saleInModel.getSp() - product.getBp();
//        Sale s = saleService.save(new Sale(saleInModel.getSp(), saleInModel.getQuantity(), new Date(), product, profit, user));
//        return new SaleResponse(s.getProduct().getLabel(), s.getProduct().getDescription(), s.getQuantity(), s.getSp() * s.getQuantity(), s.getQuantity() * (s.getSp() - s.getProduct().getBp()), s.getTimestamp(), s.getUser().getUsername());
//    }


}
