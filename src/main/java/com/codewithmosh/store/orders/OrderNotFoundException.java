package com.codewithmosh.store.orders;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("Order was not found.");
    }
}
