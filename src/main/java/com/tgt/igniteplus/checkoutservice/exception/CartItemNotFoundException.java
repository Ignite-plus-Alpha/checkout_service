package com.tgt.igniteplus.checkoutservice.exception;

public class CartItemNotFoundException extends Exception{
    public CartItemNotFoundException(String message) {
        super(message);
    }
}
