package com.shop.module.product.domain;

import lombok.*;

/**
 * Created by meg on 8/24/17.
 */

/*
        Data Transfer Object.
        object returned by the service layer and serialised to Json.
        A best practice is to structure the Dto as an immutable object
*/

@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
@Getter
public class ProductResponse {

    private int productId;

    private String label;

    private String description;

    private int bp;

    private int price;

    private String category;

    public static ProductResponse convertoDTO(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setProductId(product.getId());
        dto.setLabel(product.getLabel());
        dto.setDescription(product.getDescription());
        dto.setBp(product.getBp());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory());
        return dto;
    }
}
