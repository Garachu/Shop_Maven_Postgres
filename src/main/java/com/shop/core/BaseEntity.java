package com.shop.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by meg on 7/15/17.
 */


public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;


    protected BaseEntity() {
        id = null;
    }
}
