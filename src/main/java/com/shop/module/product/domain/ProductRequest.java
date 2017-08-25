package com.shop.module.product.domain;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 * Created by meg on 8/24/17.
 */

/*
        Data Transfer Object.
        object received by the service layer and serialised to entity.
        A best practice is to structure the Dto as an immutable object
*/

@Getter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @Size(min = 3, max = 10, message = "label should be within 8 to 10 characters")
    private String label;

    @Size(max = 20, message = "description should have 20 characters max")
    private String description;

    @Digits(integer = 6, fraction = 0, message = "The value of bp can not be more than 6 digits")
    private int bp;

    @Digits(integer = 6, fraction = 0, message = "The value of  price can not be more than 6 digits")
    private int price;

    @Size(min = 4, max = 20, message = "category should have 20 characters max")
    private String category;

    public static Product convertToEntity(ProductRequest dto){
        //Product product = modelMapper.map(dto, Product.class);
        Product product = new Product();
        product.setLabel(dto.getLabel());
        product.setDescription(dto.getDescription());
        product.setBp(dto.getBp());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setRecordstate(Boolean.TRUE);
        return product;
    }


}
