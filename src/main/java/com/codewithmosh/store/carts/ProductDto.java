package com.codewithmosh.store.carts;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {

    public Long id;
    public String name;
    public String description;
    public BigDecimal price;
    public Byte categoryId;
}
