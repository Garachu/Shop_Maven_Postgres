package com.shop.core;

import javax.persistence.*;

/**
 * Created by meg on 7/15/17.
 */

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Integer id;


    protected BaseEntity() {
        id = null;
    }
}
