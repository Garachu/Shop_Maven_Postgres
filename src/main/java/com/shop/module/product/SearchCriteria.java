package com.shop.module.product;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by meg on 7/19/17.
 */

public class SearchCriteria {
    @NotBlank(message = "Label can't empty!")
    String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
