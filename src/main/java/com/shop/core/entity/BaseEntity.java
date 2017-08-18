package com.shop.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.persistence.*;

/**
 * Created by meg on 7/15/17.
 */

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private final Integer id;


    protected BaseEntity() {
        id = null;
    }
}
