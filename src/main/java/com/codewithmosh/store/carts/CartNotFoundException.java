package com.codewithmosh.store.carts;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException() {
        super("Cart was not found");
    }
}
